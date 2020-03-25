// Copyright © 2018 Itty Bitty Apps. All rights reserved.

import Combine
import Domain
import UIKit

final class NewsViewController: UITableViewController {
  private let viewModel: NewsViewModel
  private var disposables = Set<AnyCancellable>()

  private lazy var dataSource = UITableViewDiffableDataSource<Int, NewsItem>(
    tableView: tableView,
    cellProvider: { tableView, indexPath, item in
      tableView.dequeueReusableCell(ofType: NewsItemCell.self, at: indexPath, configuredWith: item)
    }
  )

  init(viewModel: NewsViewModel) {
    self.viewModel = viewModel

    super.init(nibName: nil, bundle: nil)

    navigationItem.largeTitleDisplayMode = .always
  }

  override func viewDidLoad() {
    super.viewDidLoad()

    tableView.register(NewsItemCell.self)

    configureRefreshControl()
    configureToggle()

    // Load news from the network
    viewModel.loadNews()

    // Bindings
    viewModel.states
      .receive(on: DispatchQueue.main)
      .sink(receiveValue: { [unowned self] value in
        self.display(value)
      })
      .store(in: &disposables)

    viewModel.commands
      .receive(on: DispatchQueue.main)
      .sink(receiveValue: { [unowned self] value in
        self.execute(value)
      })
      .store(in: &disposables)
  }

  // MARK: - configure RefreshControl

  private func configureRefreshControl() {
    let refreshControl = UIRefreshControl()
    refreshControl.addTarget(self, action: #selector(refreshData(_:)), for: .valueChanged)
    self.refreshControl = refreshControl
  }

  // MARK: - configure toggle
  private func configureToggle() {
    let toggleSwitch = UISwitch()
    toggleSwitch.isOn = false
    toggleSwitch.addTarget(self, action: #selector(toggleNews(_:)), for: .valueChanged)
    let toggleButton = UIBarButtonItem(customView: toggleSwitch)
    navigationItem.rightBarButtonItem = toggleButton
  }

  @objc
  private func toggleNews(_ sender: UISwitch) {
    viewModel.showNewsWithComments(isOn: sender.isOn)
  }

  // MARK: - State

  private func display(_ state: NewsViewModel.State?) {
    guard let state = state else {
      return
    }
    title = state.title

    if state.endRefresh {
      self.refreshControl?.endRefreshing()
    }

    var snapshot = NSDiffableDataSourceSnapshot<Int, NewsItem>()
    snapshot.appendSections([0])
    snapshot.appendItems(state.items)
    dataSource.apply(snapshot, animatingDifferences: false)
  }

  // MARK: - Refresh
  @objc
  private func refreshData(_ sender: Any) {
    viewModel.refreshNews()
  }

  // MARK: - Command

  private func execute(_ command: NewsViewModel.Command?) {
    switch command {
    case .showComments(let commentsViewModel):
      let viewController = CommentsViewController(commentsViewModel)
      show(viewController, sender: self)

    default:
      break
    }
  }

  // MARK: - UITableViewDelegate

  override func tableView(_ tableView: UITableView, didSelectRowAt indexPath: IndexPath) {
    viewModel.selectItem(at: indexPath.row)
  }

  // MARK: - Unavailable

  @available(*, unavailable)
  required init?(coder: NSCoder) {
    fatalError("init(coder:) has not been implemented")
  }
}
