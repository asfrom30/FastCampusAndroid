# FastCampusAndroid
Study and Organize about Android in FastCampus

## Need to study
* App Fundamental [Link](https://developer.android.com/guide/components/fundamentals.html)
* Activity Life Cycle [Link](https://developer.android.com/guide/components/activities/activity-lifecycle.html)
* Support Library [Link](https://developer.android.com/topic/libraries/support-library/index.html)
* [Static nested class](#nestedclass) [Link](https://docs.oracle.com/javase/tutorial/java/javaOO/nested.html)

## Week1

This week is not working in Android. See [Java Lecture]()

## Week2
#### Basic Widget
* Radio Group Listener
    ```java
    public class BasicWidget implements RadioGroup.OnCheckedChangeListener {
        @Override
        public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
            if(group.getId() == R.id.radioRgbGroup){
                switch (checkedId){
                    case R.id.radioRed:
                        Toast.makeText(this, "RED.", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.radioBlue:
                        Toast.makeText(this, "BLE.", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.radioGreen:
                        Toast.makeText(this, "GREEN.", Toast.LENGTH_SHORT).show();
                        break;
                }
            }
        }
    }
    ```    

* Seek Bar
    ```java
    public class BasicWidget implements CompoundButton.OnCheckedChangeListener {
        @Override
        public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
            // seek bar에 변경사항이 있을때마다 호출한다
            seekCount.setText(progress + ""); // setText에 숫자값만 단독으로 들어가면 앱이 다운된다. (int + "")
        }

        @Override
        public void onStartTrackingTouch(SeekBar seekBar) {
        }

        @Override
        public void onStopTrackingTouch(SeekBar seekBar) {

        }
    }
    ```

    
* Toggle Button
    ```java
    public class BasicWidget implements CompoundButton.OnCheckedChangeListener {
        @Override
        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
            switch (buttonView.getId()){
                case R.id.toggleButton:
                    if(isChecked){
                        Toast.makeText(this, "켜졌습니다.", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(this, "꺼졌습니다.", Toast.LENGTH_SHORT).show();
                    }
            }
        }
    }
    ```
#### -. Basic Layout
* GridLayout
* LinearLayout
* RelativeLayout
* ConstraintLayout

#### -. Intent Filter

Run Activity

If you want to run activity stand alone, You have to add this code in **intent-filter** in **Manifest file**

            <intent-filter>
                <action android:name="android.intent.action.MAIN" />
                <category android:name="android.intent.category.LAUNCHER" /> 
            </intent-filter>
            
#### -. Intent Basic

Send SMS / MMS

    Intent it = new Intent(Intent.ACTION_VIEW);
    it.putExtra("sms_body", "The SMS text");
    it.setType("vnd.android-dir/mms-sms");
    startActivity(it);

[More example is here...](https://github.com/asfrom30/FastCampusAndroid/blob/master/app/src/main/java/com/doyoon/android/fastcampusandroid/week2/IntentExample/IntentExample.txt)

    

#### -. Adapter

#### -. RecyclerView


## Week3
### -. Tip
        
* Transaction

    That is **a Process** which handle a data at time. For example if we handle a data which needs process A,B,C
    and during the processes, if we encounter errors in process B. It goes back before Process A execution.
    For detail, we already know **CRUD**, but **Read** doesn't need Trasaction.

* XML, HTML, TXT Files

    That type of files are not compiled, So it doesn't have **Magic Number**, Only things to do in that file is declare the structure

* Depedency Injection

    Below code is **Dependency Injection**, But sometimes it's bothersome. So, some tools helps you do this easy. **Dagger** and **Butter Knife** is that type of tools.
    and Actually after **Kotlin and Android 3.0** helps you naturally. 
       
        this.editName = (EditText) findViewById(R.id.property_inputText_name);
        this.editEmail = (EditText) findViewById(R.id.property_inputText_email);
        this.editPassword = (EditText) findViewById(R.id.property_inputText_pw);

* Null Handling(**If statement** vs **Try Catch**)

    Most of case, it's better to use **if statement** for **null handling**, because sometimes **try catch statement** makes system slow
    
            // Better
            Bundle bundle = getIntent().getExtras();
            if (bundle == null) {
                return;
            }
 
            // Not recommended
            Bundle bundle = getIntent().getExtras();
            try {
                bundle.getString(/*key*/);           
            } catch (NullPointerException e){
                
            }

* Decide which function you use

    Look this code. When we get the value from `Bundle`, We use this code
    
            Bundle bundle = getIntent().getExtras();
            if (bundle == null) {
                return;
            }
            /* Bundle이 Null이면 getString()이라는 함수가 없다. */
            String value = bundle.getString("key");

    But we know another function 

            getIntent().getStringExtra("key");
    
    So, second code is look like easier to use. but sometimes we need first code.
    If we need so many values which is brought from values. In second code case, despite of `bundle` is null, they run all of `getStringExtra()`
    but first code, they don't need to every run by checking `bundle` is null
    
        //better
        Bundle bundle = getIntent().getExtras();
        if (bundle == null) {
            return;
        }
        String value1 = bundle.getString("key1");
        String value2 = bundle.getString("key2");
        String value3 = bundle.getString("key3");
        String value4 = bundle.getString("key4");
 
        //Not recommended
        String value1= getIntent().getStringExtra("key1");
        String value2= getIntent().getStringExtra("key2");
        String value3= getIntent().getStringExtra("key3");
        String value4= getIntent().getStringExtra("key4");
        
+ Get Result from Next Activity using Intent 

    Present Activity[source code](https://github.com/asfrom30/FastCampusAndroid/blob/master/app/src/main/java/com/doyoon/android/fastcampusandroid/week3/activitycontrol/ActivityControlMain.java)

    Next Activity [source code](https://github.com/asfrom30/FastCampusAndroid/blob/master/app/src/main/java/com/doyoon/android/fastcampusandroid/week3/activitycontrol/ActivityControlSub.java)

+ Present Activity 

    + Using `startActivityForResult()`, not Using `startActivity()`

            Intent intent = new Intent( /* Context */, /* Next Activity */)
            intent.putExtra("myKey", inputText.getText().toString());
            startActivityForResult(intent, /* Request Code */);

    +  Using Activity Life Cycle, `onActivityResult()` called when it appears again.

            @Override
            protected void onActivityResult(int requestCode, int resultCode, Intent data/*결과값이 담겨 온다*/) {
                super.onActivityResult(requestCode, resultCode, data);
            
                if (resultCode == RESULT_OK /* this is same as Request Code */) {
                    switch (requestCode) {
                        case BTN_RESULT :
                            int result = data.getIntExtra(/* key */, /* Default Value */);
                            break;
                        case BTN_START:
                            break;
                    }
                }
            }

+ Next Activity
    + `Intent` without `Context`, because they don't need to use **System Resource**

            Intent intent = new Intent();
            intent.putExtra(/* key */, result);
            setResult(RESULT_OK, intent);
            finish();

### -. Properties
[source code](https://github.com/asfrom30/FastCampusAndroid/blob/master/app/src/main/java/com/doyoon/android/fastcampusandroid/week3/property/PropertyActivity.java)

Actually each `Activity` can create each **Preference** but nowadays It's common to use only one **perference file** in the application.
we called that `SharedPreference`

    // Save Preference
    public void savePreference(String key, String value) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(key, value);
        editor.commit();
    }
    
    //Load Preference
    public void loadPreference() {
        String name = sharedPreferences.getString("name", "[none]");
        String email = sharedPreferences.getString("email", "[none]");
        String password = sharedPreferences.getString("password", "");

        this.editName.setText(name);
        this.editEmail.setText(email);
        this.editPassword.setText(password);

    }
    
    //Remove Preference
    private void removePreference(String key){
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.remove(key);
        editor.commit();
    }
    
    //Remove All Preference
    private void removeAllPreferences(){
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.clear();
        editor.commit();
    }

  
  
### -. WebView
[source code](https://github.com/asfrom30/FastCampusAndroid/blob/master/app/src/main/java/com/doyoon/android/fastcampusandroid/week3/webview/WebViewMain.java)

If you want to use `WebView`, You need **resource permission**. It's different form **Runtime Permission**
> Note : You need to add this code to **manifest file** to obtain permission.
>```
>  <uses-permission android:name="android.permission.INTERNET" />
>```
```
/* Dependency Injection */
webView = (WebView) findViewById(R.id.webview);

/* Create Client */
webView.setWebViewClient(new WebViewClient());
webView.setWebChromeClient(new WebChromeClient());

/* JavaScript Enable */
webView.getSettings().setJavaScriptEnabled(true);
```

### -. Runtime Permission 
Using **Contacts Permission**
[source code](https://github.com/asfrom30/FastCampusAndroid/tree/master/app/src/main/java/com/doyoon/android/fastcampusandroid/week3/runtimepermission)
* **Manifest File**
    > Add below code
    ```
    <uses-permission android:name="android.permission.CAMERA"/>
    ```
    
* `Present Activity`
    > If the version is corresponded, check permission. and then if don't have permission, request permission to User
      If have permission execution.
    ```
    /* Version Compatibilty Check statement */
    // Bring present android version and do if version is above the Mashmallow
    if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){ // After Mashmallow version, Write Initial is ok
      String permissions[] = {Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.CAMERA};
      boolean hasPermissions = PermissionUtil.hasPermissionsAndRequestIfNot(this, permissions, REQ_PERMISSION);
      if(hasPermissions){
        init();
      }
    } else {
      init();
    }
    ```

* `PermissionUtil Class`
    > `PermissionUtil.hasPermissionAndRequestIfNot()` method needs annotation `@TargetApi(/* Version */)`
It means that function can execute above **That Android Version**
    ```java
    public class PermissionUtil {
        @RequiresApi(api = Build.VERSION_CODES.M)
        public static boolean hasPermissionsAndRequestIfNot(Activity activity, String[] permissions, int requestCode) {
            Context context = activity.getBaseContext();
    
            for (String permission : permissions) {
                if(context.checkSelfPermission(permission) != PackageManager.PERMISSION_GRANTED){
                    activity.requestPermissions(permissions, requestCode);
                    return false;
                }
            }
            return true;
        }
    }
    ```

* again `Present Activity`
    > You need to override `onRequestPermissionsResult()` in the activity, This is same as **Intent For result**,
      After `activity.requestPErmission(permission, requestCode)` is called, The code which is overrided executes.
    ```
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
      super.onRequestPermissionsResult(requestCode, permissions, grantResults);
      if(requestCode == REQ_PERMISSION){
          // User accept give permission
          if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
              run();
          // User deny give permission
          } else {
              cancle();
          }
      }
    }
    ```

### -. Implicit Intent
[source code](https://github.com/asfrom30/FastCampusAndroid/blob/master/app/src/main/java/com/doyoon/android/fastcampusandroid/week3/camerapermission/CameraActivity.java)

[reference](https://developer.android.com/guide/components/intents-filters.html)

* This is the code for Pick and Returning Image
    ```java
    class Activity {
      @Override
          public void onClick(View v) {
              Intent intent = null;
              switch (v.getId()) {
                  case R.id.camera_btn_gallery :
                      intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                      startActivityForResult(Intent.createChooser(intent, "Select Photo"), 100); // for returnning Image
                      break;
                  case R.id.camera_btn_camera :
                      break;
              }
          }
          
          @Override
          protected void onActivityResult(int requestCode, int resultCode, Intent data) {
              super.onActivityResult(requestCode, resultCode, data);
              if (resultCode == RESULT_OK) {
                  switch (requestCode) {
                      case 100:
                          Uri imageUri = data.getData();
                          imageView.setImageURI(imageUri);
                          break;
          
                  }
              }
          }
    }   
    ```
### -. Manifest File
[more detail](https://developer.android.com/guide/topics/manifest/provider-element.html)
* provider

    Declares a content provider component.

### -. Content URI

Content URIs have the syntax [more detail](https://developer.android.com/reference/android/content/ContentUris.html)
    
    content://authority/path/id



### -. Content Provider
Get Contact Example

First add permission to **Manifest File**
```
<!-- 전화번호부 권한 -->
<uses-permission android:name="android.permission.READ_CONTACTS" />
```

And version and permission check. If doesn't have permission, ask user to allow to get permission in this app.

```
@Override
protected void onCreate(Bundle savedInstanceState) {
     super.onCreate(savedInstanceState);
     setContentView(R.layout.activity_recycler_again);
 
     if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
         String[] permissions = {Manifest.permission.READ_CONTACTS};
         PermissionUtil.hasPermissionsAndRequestIfNot(this, permissions, 100);
     } else {
         init();
     }
 }
 
 @Override
 public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
     super.onRequestPermissionsResult(requestCode, permissions, grantResults);
 
     if (requestCode == 100) {
         if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
             init();
         } else {
             Toast.makeText(this, "권한을 승인하셔야만 사용할 수 있습니다.", Toast.LENGTH_SHORT).show();
         }
     }
 }
```

If you want to get **Contacts** you have to use **Content Resolover**,

```
List<Contact> contactList = new ArrayList<>();
ContentResolver contentResolver = context.getContentResolver();

// Table Address
Uri phoneUri = ContactsContract.CommonDataKinds.Phone.CONTENT_URI;

// Column name from table
String proj[] = {ContactsContract.CommonDataKinds.Phone.CONTACT_ID
        , ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME
        , ContactsContract.CommonDataKinds.Phone.NUMBER};

// Bring data using Content resolver
Cursor cursor = contentResolver.query(phoneUri, proj, null, null, null);

// Check data is existed in cursor
if (cursor != null) {
    while (cursor.moveToNext()) {
        int index = cursor.getColumnIndex(proj[0]);
        String id = cursor.getString(index);

        index = cursor.getColumnIndex(proj[1]);
        String name = cursor.getString(index);

        index = cursor.getColumnIndex(proj[2]);
        String tel = cursor.getString(index);

        contactList.add(new Contact(id, name, tel));
    }
}
// Note : Should be closed
cursor.close();
```

* Non-blocking, Blocking, Channel, Stream, Database.. that type should be closed after task.
because Even if memory allocation is released, it couldn't be closed.

* Alarm, Calendar and more Content URI is [here](https://developer.android.com/reference/android/provider/package-summary.html)

* <a name="nestedclass"></a> See **Static nested class** 


### -. FileProvider and Camera

**FileProvider** is a special subclass of **ContentProvider** that facilitates secure sharing of files associated with an app by creating a **content://** Uri for a file instead of a **file:/// Uri.**

Why have to use **FileProvider** in Camera Action.

Becuase when under the Lollipop Version. Photo file can be obtained from result **Intent**. See below
```
@Override
protected void onActivityResult(int requestCode, int resultCode, Intent data) {
    super.onActivityResult(requestCode, resultCode, data);
    imageUri = data.getData();
}
```

But above the Lollipop Version, returned **Intent** which was sent as Camera Capture ```Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);``` is null.
So the way to get Uri is store Global Variable as File. and obtain Uri from File
```
Uri fileURI;        // Global Variable

public void takePhoto(){
    // Create File
    fileURI = Uri.fromFile(tempFile);
}

@Override
protected void onActivityResult(int requestCode, int resultCode, Intent data) {
    super.onActivityResult(requestCode, resultCode, data);
    if (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {
        imageUri = data.getData(); // Return URI
    } else {
        imageUri = fileUri;
    }imageUri = data.getData();
}
```

As the version updated, It is impossible obtaining URI from ```URI.fromFile()``` method after Marshmallow version, because of security issue
the way of obtaining URI is using **File Provider**
```
FileProvider.getURiForFile(Context, Authority, File);
```
> Note : File Provider is belong to **support package.**

```Autority``` name should be same as ```android:authorities``` in **Manifest File**

> Note : you have to add ```<provider>``` tag in Manifest File

In result, you have to add this code.
```
public void takePhoto(){
    // Create File
    
     /* Under Marshmallow Version doesn't need File Provider during access URI*/
    if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
        fileURI = Uri.fromFile(tempFile);
    } else { /* You have to access Using File Privider */
        // File Provider need Authority
        // Authority should be set in manifest file using <provider> tag for run below code
        String authority = BuildConfig.APPLICATION_ID + ".provider"; // get app ID from Gradle
        fileURI = FileProvider.getUriForFile((activity.getBaseContext()), authority, tempFile);
    }
}
```

So, this is all code about Using Camera

> note : 


* add **file_paht.xml** file in **xml folder** in **res folder**

        <?xml version="1.0" encoding="utf-8"?>
        <path>
            <!--실제 경로와 추상경로가 있다. 실제 매핑되어 있는 것은 ...-->
            <!--path = /Exteranal Strage/CameraPath이 된다. 갤러리 폴더가 하나 생성되어 있다.-->
            <!--name = cotnent:// 로 시작하는 uri 주소체계의 suffix가 된다.-->
            <external-path name="CameraName" path="CameraPath" />
        </path>
* add Permission in manifest file

        <!-- 카메라 권한 -->
        <uses-permission android:name="android.permission.CAMERA" />
        <!-- Exteranl Storage Write Permission -->
        <uses-permission android:name="android.permission.WRITE_EXTERNAL_STORAGE" />

* add add Provider in manifest file
    
    > note : ```android:authorities``` get its name from gradle.
    
    > note : ```android:resource="@xml/file_path"``` should be match real xml file. 
    
        <provider
            android:name="android.support.v4.content.FileProvider"
            android:authorities="${applicationId}.provider"
            android:exported="false"
            android:grantUriPermissions="true">
            <meta-data
                android:name="android.support.FILE_PROVIDER_PATHS"
                android:resource="@xml/file_path" />
        </provider>

* Main Activity(Camera Capture)
    ```
    @Override
    public void onClick(View v) {
        this.fileUri = DeviceUtil.Camera.takePhoto(this, "CameraPath", Const.Device.REQ_CODE_CAMERA);
    }
    
    /* Above the Lollipop version, Capture Camera Intent be returned as null(Intent is empty)
     * That's why FileURI set to be global variable and call Image using this variable */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == Const.Device.REQ_CODE_CAMERA) {
            if (resultCode == RESULT_OK) {
                Uri imageUri = null;
                if (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {
                    imageUri = data.getData(); // Return URI
                } else {
                    imageUri = fileUri;
                }
                imageView.setImageURI(imageUri);
            }
        }
    }
    ```    


* Camera Util (File provider and Start Activity)
    ```java
    public static class Camera{
        public static Uri takePhoto(Activity activity, String xmlExternalPath/* Folder Name */ , int requestCode) {
            Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
    
            /* Under Lollipop Version Doesn't need Convert File to URI*/
            /* Just send Intent and In onActivityResult, You can get simply File's URI using Intent.getData(); */
            if(Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP){
                activity.startActivityForResult(intent, requestCode);
                return null;
            }
    
            Uri fileURI = null;
            File tempFile = null;
    
            try {
                /* Create temp file for saving photo */
                tempFile = DeviceUtil.createFileForCamera(xmlExternalPath); //TODO : Need to change File Util
            } catch (IOException e) {
                e.printStackTrace();
            }
    
            if (tempFile == null) {
                Log.e(TAG, "Created File is null for Camera(Photo Capture)");
                return null;
            }
    
            /* Under Marshmallow Version doesn't need File Provider during access URI*/
            if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
                fileURI = Uri.fromFile(tempFile);
            } else { /* You have to access Using File Privider */
                // File Provider need Authority
                // Authority should be set in manifest file using <provider> tag for run below code
                String authority = BuildConfig.APPLICATION_ID + ".provider"; // get app ID from Gradle
                fileURI = FileProvider.getUriForFile((activity.getBaseContext()), authority, tempFile);
            }
    
            intent.putExtra(MediaStore.EXTRA_OUTPUT, fileURI);
            activity.startActivityForResult(intent, requestCode); // NOTE : Context doesn't have startActivityForResult() method.
    
            return fileURI;
        }
    }
    ```

    ```
    // 파일생성에 관한 함수를 만들고 Exception에 담는다.
    private static File createFileForCamera(String xmlExternalPath) throws IOException {
        // 임시 파일명 생성
        String tempFilename = "TEMP_" + System.currentTimeMillis();
        // 임시파일 저장용 디렉토리 생성
        // 익스터널 스토리지의 루트 경로(실제 시스템의 루트가 아님)
        // XML에서 설정했던 그 경로에 대한 권한을 얻는 것이다.
        File tempDir = new File(Environment.getExternalStorageDirectory() +"/" + xmlExternalPath + "/"); // path라는 것을 알려주기 위해 /로 랩한다
        if(!tempDir.exists()){
            tempDir.mkdir(); // 없으면 모두 생성
        }
    
        //실제 임시파일을 생성
        File tempFile = File.createTempFile(
                tempFilename,// 파일네임
                ".jpg",     // suffix
                tempDir     // 디렉토리
        );
        return tempFile;
    }
    ```
   
    
### Google Map and GPS in ViewPager


### -. Const
You can manage all **Constant Value** Using Class. This is example
```java
public class Const {
    // Grouping(namespace)
    public static class Camera {
        public static final int REQ_CAMERA = 100;
    }        
    public static class Intent {
        public static final int REQ_CODE = 101;
    }
}
```

## Week4
#### -. Thread Clock Example

#### -. Thread Communication
This Example is multiple counter. Make 4 Counter and try to each counter change each text view on couning

* Main Activity
    ```
    /* Error Counter */
    ErrorCounter errorCounter = new ErrorCounter(textViews[0]);
    new Thread(errorCounter).start();
    
    /* Way 1 - RunOnUiThread */
    RunOnUiThreadCounter runOnUiThreadCounter = new RunOnUiThreadCounter(this, textViews[1]);
    new Thread(runOnUiThreadCounter).start();
    
    /* Way 2 - Handler */
    HandlerCounter handlerCounter = new HandlerCounter(this);
    new Thread(handlerCounter).start();
    ```

* ErrorCounter

    If you try to change UI widget directly in sub thread, you encounter below error message.
    Android prevent **Sub Thread** set UI Widget directly, It is because of sync issue. 

        CalledFromWrongThreadException: Only the original thread that created a view hierarchy can touch its views.
        
    ```java
    public class ErrorCounter implements Runnable {
    
        private TextView textView;
        int count = 0;
    
        public ErrorCounter(TextView textView) {
            this.textView = textView;
        }
    
        @Override
        public void run() {
            for(int i=0; i<10; i++) {
                try {
                    Thread.sleep(500);
                } catch (Exception e){
    
                }
                this.textView.setText(count+"");  // This code occurs error
                count ++;
            }
        }
    }
    ```
        
* RunOnUiThread

    Below code may work, but issue is still here. sometimes count until 10, sometimes count until 9.
    because It can't be synced between `setText()` and `count`. `RunOnUiThread` throw their own code to main thread.
    so, sub thread doesn't have that code.
    
    ```
    this.activity.runOnUiThread(new Runnable() { // 코드로 가면 이렇게 객체를 생성하지 않고 함수를 직접 넘겨주면 된다.
        @Override
        public void run() {
            textView.setText(count+""); // 여기는 메인스레드에서 동작한다. 코드를 넘겨준다.
                                        // 메인 스레드에 붙이기 때문에.. Sub 스레드와 타이밍이 어긋난다.
                                        // 9번째 왔을때 이 코드를 메인 스레드에 던져 놓고
                                        // count++ 실행되고 이 코드가 실행하면 10이 찍히고
                                        // count++가 실행되기 전에 이코드가 실행되면 9가 찍힌다.
        }
    });
    ```
 
* Handler

    Using Handler and Looper

    ```java
    // Handler 클래스를 전달 받아서.. 서브 thread로 부터 메세지를 전달받을 Handler를 생성한다... 메세지 통신
    public class HandlerCounter extends  Handler implements Runnable{
    
        public static final int SET_COUNT = 99;
        private Activity activity;
        private int count = 0;
    
        public HandlerCounter(Activity activity) {
            this.activity = activity;
        }
    
        @Override
        public void run() {
            for(int i=0; i < 10; i ++) {
                try {
                    Thread.sleep(500);
                } catch (Exception e) {
                    e.printStackTrace();
                }
    
                // 핸들러를 통해 메세지를 날릴때 Msg를 생성해서 날릴 수 있따.
                // 메세직 객체가 그렇게 설계가 되어 있다. arg1, arg2 두개의 argument를 날려줄 수 있다.
                Message msg = new Message();
                msg.what = SET_COUNT;
                msg.arg1 = count;
                // msg.arg1 = integer;
                // msg.arg2 = integer;
                // msg.setData();       // send Instance
                sendMessage(msg);
                /* Empty Message */
                // sendEmptyMessage(SET_COUNT);
    
                count++;
            }
        }
    
        // 서브 thread에서 메세지를 전달하면 handleMessage 함수가 동작한다.
        @Override
        public void handleMessage(Message msg) {
            // 메세지가 정해져 있따.
            // msg.arg1;
            // msg.arg2;
    
            // 자바에서는 주로 integer로 주고 받는다,
            // 핸들러간 통신을 할때는 미리 상수로 정의 해 놓는다.
    
            switch (msg.what) {
                case SET_COUNT:
                    int tempCount = msg.arg1;
                    ((TextView) activity.findViewById(R.id.multipleCount_tv_3)).setText(tempCount + "");
                    break;
            }
    
        }
    }
    ```

* Interthread

    [Link](https://stackoverflow.com/questions/5916370/how-to-use-notify-and-wait)
    
    `wait(), notify(), syncTocken`


#### -. Paint Property

```java
public class PaintExample {
    public PaintExample() {
        Paint paint = new Paint();
        paint.setStyle(Paint.Style.STROKE);
        paint.setAntiAlias(true);
        paint.setStrokeJoin(Paint.Join.ROUND);
        paint.setStrokeCap(Paint.Cap.ROUND);
        paint.setDither(true);
    }
}
```








