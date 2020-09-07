# secand
secand is short for *SECure ANDroid*. It's basically an easy approach to improve your android application security. If you familiar with the term of **Penetration Testing**, you must also realize a good code is not enough. By implementing security, it helps you and your apps from anyone out there that try to crack your app.
<br/>

*enough with the talk, show me how it's done!*
<br/><br/>

## Download
- Well first, add this line to your root `build.gradle` file:
  ```gradle
    allprojects {
    repositories {
        ..
        ..
        maven { url "https://jitpack.io" }
      }
  }
  ```
 - Next, add this line to your app `build.gradle` file:
   ```gradle
      dependencies {
        ..
        ..
        implementation 'com.github.jimlyas:secand:${LatestVersion}'
      }
   ```
   the latest release is [![Release](https://jitpack.io/v/jimlyas/secand.svg)](https://jitpack.io/#jimlyas/secand)
 - Gradle sync, and that's it! you can start implementing this library to your android project.
<br/><br/>

## Implementation
Here are things this library will help you to secure your apps:

### Root and Emulator Detection
It's quite a security issue when your apps installed on rooted device or emulator.In root or emulator, device data is at risk, including gaining access to personal information such as contact lists, emails, and other data, or collecting data like credentials and passwords. With a rooted device, user or malicious program can elevate their permissions to root and circumvent this protection giving them access to other app’s private data.<br/>
So what you want to do is to check if your apps is installed on one of those things and handle them nicely. You can detect it by using this line:
```kotlin
when (Secand.check(context)) {
    EMULATOR -> // Define what to do when the device is emulator
    ROOTED -> // Define what to do when the device is rooted
    else -> // (Optional) it means the device is safe
 }
```
### Obfuscation
Ever heard of **Reverse-Engineering**? Simply it means decompile your apk file to it's original form which is *the source code*. It's a nightmare if you store your credentials, your endpoints, or even your database password inside the source code and not do anything to hide it from the act of reverse-engineering.<br/>
By using Proguard, this library will **automatically** obfuscate your code when you enable it from your app `build.gradle` like this:
```gradle
buildTypes {
        debug {
            minifyEnabled false //set to true to enable proguard
            debuggable true
            proguardFiles getDefaultProguardFile('proguard-android-optimize.txt'), 'proguard-rules.pro'
        }
        release {
            minifyEnabled true //set to true to enable proguard
            debuggable false
            proguardFiles getDefaultProguardFile('proguard-android-optimize.txt'), 'proguard-rules.pro'
        }
    }
```
Proguard is all about rules, You provide it with set of rules of what you want to obfuscate and what you don't want to. 
> This library defines basic of those rules. You **may need to add another rules** to your app `proguard-rules.pro` when adding another library  
<br/>

 Proguard able to obfuscate class name, method name, and variable name. **But it can't** obfuscate the value of a variable. Let's take a look:
 ```kotlin
   //Before obfuscation
   val name = "Jimly Asshiddiqy"

   //After obfuscation
   val bw0 = "Jimly Asshiddiqy"
  ```
  It become a problem when *for example* you store your base url in a variable. Simply store your string to `strings.xml` and this library will handle the obfuscation for you.
  ### Secure Preference
  It's common to store variables to `SharedPreference`. But it's also known that the value that we store can be accessed through directory. To handle this, we can use encryption to make it imposibble to read what is it that we store in `SharedPreference`.
  ```kotlin
  // Initialize class like this
  val mPrefs = PreferenceManager(context, "preferenceName", Gson())
  
  //And then use it to store your preference
  mPrefs.saveString("keyPreference","valuePreference")
  ```
  Your Preference will look something like this and will be harder to read:
  ```xml
    <?xml version='1.0' encoding='utf-8' standalone='yes' ?>
     <map>
      <!-- storage random salt -->
      <string name="23rtgegr4d5682ace841fr34t5y645u765ytgrfd">riIPjrL2WRfoh8QJXu7fWk4GGeAKlQoJl9ofABHZKlc=</string>
      <!-- 'key1':'stringValue' -->
      <string name="152b866fd2d63899678c21f247bb6df0d2e38072">AAAAABD/8an1zfovjJB/2MFOT9ncAAAAJaf+Z9xgzwXzp1BqTsVMnRZxR/HfRcO8lEhyKpL17QmZ5amwAYQ=</string>
      </map>
    </xml>
  ```
  <br/><br/>
  
  ## Other things you can do
  This library can't cover all the security for you, *that's true*. Because there are some things you need to configure it yourself on your `app` module. Here are other things   you can do to improve your android app security: 
  - SSL Pinning, It's quite easy actually. you can read how to do that [here](https://www.netguru.com/codestories/3-ways-how-to-implement-certificate-pinning-on-android)
  - Here's another tip from [Android Developer](https://developer.android.com/training/articles/security-tips)
