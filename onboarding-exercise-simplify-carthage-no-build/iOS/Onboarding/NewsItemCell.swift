// Copyright © 2020 Itty Bitty Apps. All rights reserved.

import UIKit

final class NewsItemCell: UITableViewCell, ModelConfigurable {
  private let titleLabel = UILabel()
  private let subtitleLabel = UILabel()

  override init(style: UITableViewCell.CellStyle, reuseIdentifier: String?) {
    super.init(style: style, reuseIdentifier: reuseIdentifier)

    // Labels
    titleLabel.font = .preferredFont(forTextStyle: .subheadline, compatibleWith: traitCollection)
    titleLabel.numberOfLines = 0
    titleLabel.setContentHuggingPriority(.defaultHigh, for: .vertical)

    subtitleLabel.font = .preferredFont(forTextStyle: .caption1, compatibleWith: traitCollection)
    subtitleLabel.numberOfLines = 0

    // Separator
    let separator = UIView()
    separator.translatesAutoresizingMaskIntoConstraints = false
    separator.backgroundColor = .separator

    let separatorConstraints = [
      separator.heightAnchor.constraint(equalToConstant: 1.0 / UIScreen.main.scale),
      separator.leadingAnchor.constraint(equalTo: contentView.leadingAnchor),
      separator.trailingAnchor.constraint(equalTo: contentView.trailingAnchor),
      separator.bottomAnchor.constraint(equalTo: contentView.bottomAnchor)
    ]

    // Stacks
    let verticalStack = UIStackView(arrangedSubviews: [titleLabel, subtitleLabel])
    verticalStack.translatesAutoresizingMaskIntoConstraints = false
    verticalStack.axis = .vertical
    verticalStack.spacing = 8

    let layoutGuide = contentView.layoutMarginsGuide

    let verticalStackConstraints = [
      verticalStack.leadingAnchor.constraint(equalTo: layoutGuide.leadingAnchor),
      verticalStack.topAnchor.constraint(equalTo: layoutGuide.topAnchor),
      verticalStack.trailingAnchor.constraint(equalTo: layoutGuide.trailingAnchor),
      verticalStack.bottomAnchor.constraint(equalTo: layoutGuide.bottomAnchor)
    ]

    // Layout
    contentView.addSubview(separator)
    contentView.addSubview(verticalStack)
    NSLayoutConstraint.activate(separatorConstraints + verticalStackConstraints)
  }

  func configure(with model: NewsItem) {
    titleLabel.text = model.title
    subtitleLabel.text = model.subTitle
    accessoryType = model.showsDisclosure ? .disclosureIndicator : .none
  }

  // MARK: - Unavailable

  @available(*, unavailable)
  required init?(coder: NSCoder) {
    fatalError("init(coder:) has not been implemented")
  }
}
