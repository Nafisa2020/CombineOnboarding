//  Copyright © 2018 Itty Bitty Apps. All rights reserved.

import UIKit

class CommentsViewController: UIViewController {
  private let viewModel: CommentsViewModel

  init(_ viewModel: CommentsViewModel) {
    self.viewModel = viewModel

    super.init(nibName: nil, bundle: nil)

    navigationItem.largeTitleDisplayMode = .never
  }

  override func loadView() {
    let view = UIView()
    view.backgroundColor = .white
    self.view = view
  }

  override func viewDidLoad() {
    super.viewDidLoad()

    // Bindings
    let display = { [unowned self] in self.display($0) }
    viewModel.$state
      .receive(on: DispatchQueue.main)
      .sink(receiveValue: display)
  }

  private func display(_ state: CommentsViewModel.State) {
    title = state.title
  }

  // MARK: - Unavailable

  @available(*, unavailable)
  required init?(coder: NSCoder) {
    fatalError("init(coder:) has not been implemented")
  }
}
