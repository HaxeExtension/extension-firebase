#ifndef EXTENSION_FIREBASE_APP_DELEGATE_H
#define EXTENSION_FIREBASE_APP_DELEGATE_H

#import <UIKit/UIKit.h>

@interface FirebaseAppDelegate : UIResponder<UIApplicationDelegate>

+ (instancetype)sharedInstance;

- (BOOL)application:(UIApplication *)application willFinishLaunchingWithOptions:(NSDictionary *)launchOptions;

- (BOOL)sendFirebaseAnalyticsEvent:(NSString*)eventName jsonPayload:(NSString *)jsonPayload;

- (NSString*)getInstanceIDToken;

@end

#endif
