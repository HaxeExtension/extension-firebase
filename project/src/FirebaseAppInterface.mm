#include "FirebaseAppDelegate.h"
#include "FirebaseAppInterface.h"

@implementation FirebaseAppInterface : NSObject
@end

namespace extension_ios_firebase {

    static value sendFirebaseAnalyticsEvent(value eventName, value jsonPayload) {
        NSLog(@"extension_ios_firebase sendAnalyticsEvent");
        return alloc_bool([[FirebaseAppDelegate sharedInstance]
            sendFirebaseAnalyticsEvent:[NSString stringWithUTF8String:val_string(eventName)]
            jsonPayload:[NSString stringWithUTF8String:val_string(jsonPayload)]
        ]);
    }

    static value getInstanceIDToken() {
        NSLog(@"extension_ios_firebase getInstanceIDToken");
        NSString* idToken = [[FirebaseAppDelegate sharedInstance] getInstanceIDToken];
        return alloc_string([idToken UTF8String]);
    }
}
