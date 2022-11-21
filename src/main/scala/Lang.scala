package diamond

// TODO: add F-sub style subtyping
// TODO: add qualified types (QType)
// TODO: add algebraic data types
// TODO: type lambda self-reference?
// TODO: multi-argument lambdas
// TODO: ELam has self-reference thus can be recursive, do we need return type annotated?

enum Type:
  case TUnit
  case TNum
  case TBool
  case TVar(x: String)
  case TFun(t1: Type, t2: Type)
  case TForall(x: String, t: Type)

case class QType(t: Type, q: Set[String])

enum Expr:
  case EUnit
  case ENum(n: Int)
  case EBool(b: Boolean)
  case EVar(x: String)
  case EBinOp(op: String, e1: Expr, e2: Expr)
  case ELam(f: String, x: String, t: Type, body: Expr)
  case EApp(e1: Expr, e2: Expr)
  case ELet(x: String, t: Type, rhs: Expr, body: Expr)
  case ETyLam(tx: String, body: Expr)
  case ETyApp(e: Expr, t: Type)
  case EAlloc(init: Expr)
  case EAssign(lhs: Expr, rhs: Expr)
  case EDeref(e: Expr)
  case ECond(cnd: Expr, thn: Expr, els: Expr)
