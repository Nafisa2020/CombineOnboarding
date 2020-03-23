// Copyright © 2018 Itty Bitty Apps. All rights reserved.

protocol ModelConfigurable {
  associatedtype Model

  func configure(with model: Model)
}
