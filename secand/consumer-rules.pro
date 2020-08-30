-keep public class * extends androidx.appcompat.app.AppCompatActivity
-keep public class * extends android.app.Application
-keep public class * extends android.app.Service
-keep public class * extends android.content.BroadcastReceiver
-keep public class * extends android.content.ContentProvider
-keep public class * extends android.app.backup.BackupAgentHelper
-keep public class * extends android.preference.Preference

-dontwarn android.support.design.**
-dontwarn org.w3c.dom.bootstrap.DOMImplementationRegistry
-dontwarn com.fasterxml.jackson.databind.**
-dontwarn rx.**
-dontwarn okio.**
-dontwarn kotlin.**
-dontwarn autovalue.shaded.**
-dontwarn org.apache.http.**
-dontwarn org.apache.velocity.**
-dontwarn android.net.http.AndroidHttpClient
-dontwarn com.squareup.javapoet.**
-dontwarn com.google.errorprone.annotations.*
-dontwarn com.google.common.**
-dontwarn com.google.googlejavaformat.**
-dontwarn com.google.android.gms.**
-dontwarn com.google.auto.**
-dontwarn com.google.gson.**
-dontwarn com.google.common.annotations.**
-dontwarn com.android.volley.toolbox.**
-dontwarn javassist.**
-dontwarn org.nustaq.**
-dontwarn org.objenesis.**
-dontwarn org.bouncycastle.**
-dontwarn rocks.xmpp.addr.**
-dontwarn org.eclipse.**

-dontnote org.apache.**
-dontnote android.net.**
-dontnote com.google.gson.**
-dontnote com.fasterxml.**
-dontnote kotlin.**

# Kotlin
-keep class kotlin.* { *; }
-keep class kotlin.Metadata { *; }
-dontwarn kotlin.**
-keepclassmembers class **$WhenMappings {
    <fields>;
}
-keepclassmembers class kotlin.Metadata {
    public <methods>;
}
-assumenosideeffects class kotlin.jvm.internal.Intrinsics {
    static void checkParameterIsNotNull(java.lang.Object, java.lang.String);
}

# ANDROIDX
-keep class com.google.android.material.* { *; }

-dontwarn com.google.android.material.**
-dontnote com.google.android.material.**

-dontwarn androidx.**
-keep class androidx.* { *; }
-keep interface androidx.* { *; }

-keepclassmembers enum * {
    public static **[] values();
    public static ** valueOf(java.lang.String);
}

-keepclassmembers class * extends java.lang.Enum {
    <fields>;
    public static **[] values();
    public static ** valueOf(java.lang.String);
}

-keepclasseswithmembernames class * {
    native <methods>;
}

#RxJava
-dontwarn sun.misc.**

-keepclassmembers class rx.internal.util.unsafe.*ArrayQueue*Field* {
   long producerIndex;
   long consumerIndex;
}
-dontnote rx.internal.util.PlatformDependent


#Remove debug logs
-assumenosideeffects class android.util.Log {
    public static boolean isLoggable(java.lang.String, int);
    public static *** v(...);
    public static *** i(...);
    public static *** w(...);
    public static *** d(...);
    public static *** e(...);
}

# Okhttp 3
-keep class okhttp3.* { *; }
-keep interface okhttp3.* { *; }
-dontwarn okhttp3.**


# Retrofit
-dontwarn retrofit2.**
-keep class retrofit2.* { *; }
-keepclasseswithmembers class * {
    @retrofit2.http.* <methods>;
}


#Testing
-dontnote junit.framework.**
-dontnote junit.runner.**
-dontwarn android.test.**
-dontwarn android.support.test.**
-dontwarn org.junit.**
-dontwarn org.hamcrest.**

#Anko
-dontwarn org.jetbrains.anko.**

#Gson
-keepattributes Signature
-keepattributes *Annotation*
-dontwarn sun.misc.**
-keep class com.google.gson.examples.android.model.* { <fields>; }