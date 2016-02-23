// package meowsynth

// object KeyboardAndPaws {
//   case class State(leftHand: Int, rightHand: Int)

//   case class Backend(scope: BackendScope[State, State]) {
//     def tick =
//       scope.modState(s => s.copy(leftHand = s.leftHand + 1, rightHand = s.rightHand + 1))

//     def render =
//       <.div(s"Left ${scope.state.leftHand} right ${scope.state.rightHand}")
//   }

//   val Component =
//     ReactComponentB[State]("KeyboardAndPaws")
//       .initialState(State(0, 0))
//       .backend(Backend)
//       .render(_.backend.render).build
// }
