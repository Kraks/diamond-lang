---
theme: white
timeForPresentation: 600
width: 1600
height: 900
---

# Checking Reachability Types

Songlin Jia

27 April, 2023

--

## Content

<split even>

::: block

Overview
- Why Reachability Types
- Typing Rules
- Goal: Checking

:::
::: block

Current Status
- Language Specification
- Algorithmic Rules
- Metatheories

:::
::: block

Moving Forward
- Lessons Learned
- Future Work

:::
</split>

---

## Why Reachability Types

To track overlapping,

$$\texttt{foo}(\texttt{x: X}^{b, c}, \texttt{ y: Y}^b, \texttt{ z: Z}^c)$$

and separation,

$$\texttt{foo}(\texttt{x: X}^{\scriptscriptstyle\blacklozenge}, \texttt{ y: Y}^b, \texttt{ z: Z}^c)$$

--

## Typing Rules

<split even>

::: block

- with Qualifiers and $\scriptstyle\blacklozenge$
- with References
- Abstraction \& Application
	- Self-References
	- Dependent Types
- One-Step Reachability

:::
::: block

<!-- [[Pasted image 20230425122435.png]] -->

$$\frac{\Gamma^\varphi\vdash t: T^q \qquad {\scriptstyle\blacklozenge}\notin q}{\Gamma^\varphi\vdash\texttt{ref }t: (\texttt{Ref }T^q)^{{\scriptscriptstyle\blacklozenge}q}} \qquad \text{(T-Ref)}$$

$$\frac{x: T^q\in \Gamma \qquad x\in\varphi}{\Gamma^\varphi\vdash x: T^x} \qquad \text{(T-Var)}$$

$$\frac{(\Gamma,f:F,x:P)^{q,x,f}\vdash t:Q \qquad q\subseteq\varphi \qquad F=(f(x: P)\to Q)^P}{\Gamma^\varphi\vdash\lambda f(x).t:F} \qquad \text{(T-Abs)}$$

$$\frac{\Gamma^\varphi\vdash t_1:(f(x:T^p)\to Q)^q \quad \Gamma^\varphi\vdash t_2: T^p \quad {\scriptstyle\blacklozenge}\notin p \quad Q=U^r \quad r\subseteq{\scriptstyle\blacklozenge}\varphi,x,f}{\Gamma^\varphi\vdash t_1t_2: Q[p/x,q/f]} \qquad \text{(T-App)}$$

:::
</split>

--

### More than One-Step: Subtyping

<split even>

::: block

<!-- [[Pasted image 20230425125137.png]] -->

$$\frac{\Gamma^\varphi\vdash t:Q \qquad \Gamma\vdash Q<:T^q \qquad q\subseteq{\scriptstyle\blacklozenge}\varphi}{\Gamma^\varphi\vdash t:T^q} \qquad \text{(T-Sub)}$$

$$\frac{\Gamma\vdash S<:T \qquad \Gamma\vdash p<:q}{\Gamma\vdash S^p <: T^q} \qquad \text{(SQ-Sub)}$$

:::
::: block

<!-- [[Pasted image 20230425125323.png]] -->

$$\frac{p\subseteq q\subseteq{\scriptstyle\blacklozenge}dom(\Gamma)}{\Gamma\vdash p<:q} \qquad \text{(Q-Sub)}$$

$$\frac{f:T^q\in\Gamma \qquad {\scriptstyle\blacklozenge}\notin q}{\Gamma\vdash p,q,f<:p,f} \qquad \text{(Q-Self)}$$

$$\frac{x:T^q\in\Gamma \qquad {\scriptstyle\blacklozenge}\notin q}{\Gamma\vdash p,x<:p,q} \qquad \text{(Q-Var)}$$

$$\frac{\Gamma\vdash p<:q \qquad \Gamma\vdash q<:r}{\Gamma\vdash p<:r} \qquad \text{(Q-Trans)}$$

:::
</split>


--

## Goal

With type safety proven, try to implement type checking.

---

## Language Specification

- AST embedded in Scala 3
- STLC + Boolean + Number + References
	- with `let`, `cond`, and arithmetic
- without type synthesis
- $\alpha$-equivalence by renaming
- Test cases implemented
- Can be extended with System $F_{<:}$ (WIP)

--

## Algorithmic Rules

Mostly syntax-guided, except for sub-qualifier checking.

<!-- [[Pasted image 20230425125323.png]] -->

$$\frac{p\subseteq q\subseteq{\scriptstyle\blacklozenge}dom(\Gamma)}{\Gamma\vdash p<:q} \qquad \text{(Q-Sub)}$$

$$\frac{f:T^q\in\Gamma \qquad {\scriptstyle\blacklozenge}\notin q}{\Gamma\vdash p,q,f<:p,f} \qquad \text{(Q-Self)}$$

$$\frac{x:T^q\in\Gamma \qquad {\scriptstyle\blacklozenge}\notin q}{\Gamma\vdash p,x<:p,q} \qquad \text{(Q-Var)}$$

$$\frac{\Gamma\vdash p<:q \qquad \Gamma\vdash q<:r}{\Gamma\vdash p<:r} \qquad \text{(Q-Trans)}$$

--

### Solution

~~We use equality saturation on both sides.~~

We have an asymmetrical solution.

$$\frac{\Gamma\,\vdash\,q\,\Uparrow\,q' \qquad \Gamma\,\vdash\,x_{x \in p}\,\ll\,q'}{\Gamma\,\vdash\,p\,<\,q}$$

--

### Expand the Right

Downcast RHS by `Q-Self:` $q,f <: f$

$$\frac{f_{\,f\,\in\,q}\,:\,T^p\,\in\,\Gamma \qquad \blacklozenge\,\notin\,p \implies p\,\subseteq\,q}{\Gamma\,\vdash\,q\,\Uparrow\,q} \qquad \text{(satruated)}$$

$$\frac{f_{\,f\,\in\,q}\,:\,T^{p'}\,\in\,\Gamma \qquad \blacklozenge\,\notin\,p' \qquad p'\,\nsubseteq\,p,f \qquad \Gamma\,\vdash\,p,p',f\,\Uparrow\,q}{\Gamma\,\vdash\,p,f\,\Uparrow\,q} \qquad \text{(expansion)}$$
 
--

### Upcast the Left

Upcast LHS by `Q-Var:` $x <: q$

$$\frac{x\,\in\,q}{\Gamma\,\vdash\,x\,\ll\,q} \qquad \text{(bounded)}$$

$$\frac{x\,\notin\,q \qquad x\,:\,T^p\,\in\,\Gamma \qquad \blacklozenge\,\notin\,p \qquad \Gamma\,\vdash\,y_{\,y \in p}\,\ll\,q}{\Gamma\vdash x \ll q} \qquad \text{(upcasting)}$$

--

## Metatheories

We wish all the best for our solution
- **Soundness**: $\Gamma\vdash p < q \implies \Gamma\vdash p <: q$
- **Completeness**: $\Gamma\vdash p <: q \implies \Gamma\vdash p < q$
- **Termination**: $\Gamma\vdash p < q$ can be decided in bounded time
+ **Soundness** and **Completeness** proven, **Termination** holds but is hard

--

### Proof Details

- Algorithmic rules defined by inductive propositions
- Qualifiers modeled by MSets in Coq
- Heavily using inductions
	- Declarative sub-qualifier rules
	- Algorithmic sub-qualifier rules
	- Induction principle `Set.fold_rec`
- Program Fixpoints can be used to prove **Termination** but not others
- A proper termination proof would require well-founded recursion

---

## Lessons Learned

- Type checking can be non-trivial, sometimes undecidable
	- e.g. Full $F_{<:}$ subtyping
- Proofs in Coq heavily rely on encoding of things
	- e.g. functions, sets, ...
	- Extensional Equality

--

## Future Work

- System $F_{<:}$ extension
- Data structure encoding
- Function self-reference design