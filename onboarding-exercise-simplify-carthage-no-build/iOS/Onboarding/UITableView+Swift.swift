// Copyright © 2018 Itty Bitty Apps. All rights reserved.

import UIKit

extension UITableView {
  func register(_ cellClass: AnyClass) {
    let identifier = String(describing: cellClass)
    register(cellClass, forCellReuseIdentifier: identifier)
  }

  func dequeueReusableCell<Cell>(
    ofType type: Cell.Type,
    at indexPath: IndexPath
  ) -> Cell where Cell: UITableViewCell {
    let identifier = String(describing: type)
    let cell = dequeueReusableCell(withIdentifier: identifier, for: indexPath)
    return cell as! Cell // swiftlint:disable:this force_cast
  }

  func dequeueReusableCell<Cell>(
    ofType type: Cell.Type,
    at indexPath: IndexPath,
    configuredWith model: Cell.Model
  ) -> Cell where Cell: UITableViewCell & ModelConfigurable {
    let cell = dequeueReusableCell(ofType: type, at: indexPath)
    cell.configure(with: model)
    return cell
  }
}
