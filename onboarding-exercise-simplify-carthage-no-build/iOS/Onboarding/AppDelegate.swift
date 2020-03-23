// Copyright © 2018 Itty Bitty Apps. All rights reserved.

import Domain
import UIKit

@UIApplicationMain
class AppDelegate: UIResponder, UIApplicationDelegate {
  var window: UIWindow?

  func application(
    _ application: UIApplication,
    didFinishLaunchingWithOptions launchOptions: [UIApplication.LaunchOptionsKey: Any]? = nil
  ) -> Bool {
    // Initial View Controller
    let newsViewModel = NewsViewModel(service: HackerNewsService())
    let newsViewController = NewsViewController(viewModel: newsViewModel)

    let navigationController = UINavigationController(rootViewController: newsViewController)

    // Setup Navigation Bar appearance
    let navBarAppearance = UINavigationBarAppearance()
    navBarAppearance.configureWithOpaqueBackground()
    navBarAppearance.titleTextAttributes = [.foregroundColor: UIColor.white]
    navBarAppearance.largeTitleTextAttributes = [.foregroundColor: UIColor.white]
    navBarAppearance.backgroundColor = UIColor(red: 1, green: 102 / 255, blue: 0, alpha: 1)

    navigationController.navigationBar.standardAppearance = navBarAppearance
    navigationController.navigationBar.scrollEdgeAppearance = navBarAppearance
    navigationController.navigationBar.compactAppearance = navBarAppearance

    navigationController.navigationBar.prefersLargeTitles = true
    navigationController.navigationBar.tintColor = .white

    // Present Window
    let window = UIWindow()
    window.rootViewController = navigationController
    window.makeKeyAndVisible()
    self.window = window

    return true
  }
}
