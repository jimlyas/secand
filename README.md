# secand
secand is short for *SECure ANDroid* is basically an easy approach to improve your android application security. If you familiar with the term of **Penetration Testing**, you must also realize a good code is not enough. By implementing security, it helps you and your apps from anyone out there that try to crack your app.
<br/><br/>
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
 - Gradle sync, and that's it! you can start implementing this library to your android project.
<br/><br/>

## Implementation
Here are things this library will help you to secure your apps:

### Root and Emulator Detection
It's quite a security issue when your apps installed on rooted device or emulator.In root or emulator, device data is at risk, including gaining access to personal information such as contact lists, emails, and other data, or collecting data like credentials and passwords. With a rooted device, a user or malicious program can elevate their permissions to root and circumvent this protection giving them access to other app’s private data.<br/>
So what you want to do is to check if your apps is installed on one of those things and handle them nicely. You can detect it by using this line:
```kotlin
when (Secand.check(this)) {
    EMULATOR -> // Define what to do when the device is emulator
    ROOTED -> // Define what to do when the device is rooted
    else -> // (Optional) it mean the device is safe
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
> This library defines basic of those rules. You **may need to define another rules** to your app `proguard-rules.pro` when adding another library  
<br/>

 Proguard able to obfuscate class name, method name, and variable name. **But it can't** obfuscate the value of a variable. Let's take a look:
 ```kotlin
   //Before obfuscation
   val name = "Jimly Asshiddiqy"

   //After obfuscation
   val bw0 = "Jimly Asshiddiqy"
  ```
  It become a problem when *for example* you store your base url in a variable. Simply store your string to `strings.xml` and this library will handle the obfuscation for you.
  
  ### SSL Pinning
  *Coming soon...*
