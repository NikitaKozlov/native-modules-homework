import UIKit
import React

@objc(RNCustomButtonViewManager)
final class RNCustomButtonViewManager: RCTViewManager {
  override func view() -> UIView {
    return RNCustomButton()
  }

  @objc override static func requiresMainQueueSetup() -> Bool {
    true
  }

  override class func moduleName() -> String {
    return "RNCustomButtonViewManager"
  }
}