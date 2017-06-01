# FastCampusAndroid
Study and Organize about Android in FastCampus

## week1

## Week2

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
```java
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
    ```java
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
    ```
