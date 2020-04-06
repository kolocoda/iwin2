# Add project specific ProGuard rules here.
# By default, the flags in this file are appended to flags specified
# in C:\Users\Chyma\AppData\Local\Android\sdk/tools/proguard/proguard-android.txt
# You can edit the include path and order by changing the proguardFiles
# directive in build.gradle.
#
# For more details, see
#   http://developer.android.com/guide/developing/tools/proguard.html

# Add any project specific keep options here:

# If your project uses WebView with JS, uncomment the following
# and specify the fully qualified class name to the JavaScript interface
# class:
 -keepclassmembers class fqcn.of.javascript.interface.for.webview {
   public *;
}
-keep public class * extends android.app.AppCompatActivity
-keep public class * extends android.app.Application
-keep public class * extends android.app.Service
-keep public class * extends android.content.BroadcastReceiver
-keep public class * extends android.content.ContentProvider
-keep public class * extends android.preference.Preference
-keep public class com.android.vending.licensing.*

-keep class com.j256.**
-keepclassmembers class com.j256.** {*;}
-keep enum com.j256.**
-keepclassmembers enum com.j256.**{*;}
-keep interface com.j256.**
-keepclassmembers interface com.j256.**{*;}

-keep class com.google.gson.**
-keepclassmembers class com.google.gson.** {*;}
-keep enum com.google.gson.**
-keepclassmembers enum com.google.gson.**{*;}
-keep interface com.google.gson.**
-keepclassmembers interface com.google.gson.**{*;}
-keepattributes Signature
-keepattributes *Annotation*

-keep class android.support.v4.app.**{*;}
-keep interface android.support.v4.app.**{*;}

-keep public class * extends android.view.View {
    public <init>(android.content.Content);
    public <init>(android.content.Content, android.util.AttributSet);
    public <init>(android.content.Content, android.util.AttributSet, int);
}
-keepclasseswithmembers class * {
    public <init>(android.content.Content, android.util.AttributSet);
}
-keepclasseswithmembers class * {
    public <init>(android.content.Content, android.util.AttributSet, int);
}
-keepclasseswithmembers class * {
    public void *(android.view.View);
    public void *(android.view.MenuItem);
}