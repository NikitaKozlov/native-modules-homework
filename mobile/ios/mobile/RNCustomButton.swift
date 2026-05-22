import UIKit
import React

@objc(RNCustomButton)
final class RNCustomButton: UIButton {

  @objc var onPress: RCTBubblingEventBlock?

  @objc var title: String = "" {
    didSet {
      setTitle(title, for: .normal)
    }
  }

  @objc var disabled: Bool = false {
    didSet {
      if(disabled.boolValue) {
        self.isEnabled = false
        self.backgroundColor = .gray
        self.setTitleColor(.lightGray, for: .normal)
    
      } else {
        self.isEnabled = true
        self.backgroundColor = .white
        self.setTitleColor(.black, for: .normal)
      }
    }
  }

  override init(frame: CGRect) {
    super.init(frame: frame)
    addTarget(self, action: #selector(handlePress), for: .touchUpInside)
  }

  required init?(coder: NSCoder) {
    fatalError("init(coder:) has not been implemented")
  }


  @objc
  private func handlePress() {
    if (!disabled) {
      onPress?([:])
    }
  }
}