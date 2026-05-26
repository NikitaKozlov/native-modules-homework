import Foundation
import UIKit
import UserNotifications

@objc(Notification)
class Notification: NSObject {

  @objc
  static func moduleName() -> String! {
    return "Notification"
  }

  @objc
  func requestPermissions() {
    let center = UNUserNotificationCenter.current()
    center.getNotificationSettings { settings in
      center.requestAuthorization(options: [.alert, .sound, .badge]) { _, _ in
        center.getNotificationSettings { newSettings in
          print("Notification settings: \(newSettings.authorizationStatus.rawValue)")
        }
      }
    }
  }

  @objc
  func showNotification(_ title: String, location body: String) {

    let content = UNMutableNotificationContent()
    content.title = title
    content.body = body
    content.userInfo = ["foreground": true]

    let trigger = UNTimeIntervalNotificationTrigger(timeInterval: 2, repeats: false)
    let request = UNNotificationRequest(identifier: UUID().uuidString, content: content, trigger: trigger)


    let center = UNUserNotificationCenter.current()
    DispatchQueue.main.async {
      center.add(request) { _ in
        UNUserNotificationCenter.current().delegate = self
      }
    }

  }

  @objc
  static func requiresMainQueueSetup() -> Bool {
    return true
  }
}

extension Notification: UNUserNotificationCenterDelegate {
  func userNotificationCenter(_ center: UNUserNotificationCenter, willPresent notification: UNNotification, withCompletionHandler completionHandler: @escaping (UNNotificationPresentationOptions) -> Void) {
    print("Will present notification in foreground")
    completionHandler([.banner, .sound, .badge])
  }
}