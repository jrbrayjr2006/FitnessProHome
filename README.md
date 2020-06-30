Fitness Pro Home
================

## Overview

This is a rebuild of the Fitness Pro application using Kotlin instead of Java and implementing modern Android libraries and styles.

## Technical Details

### Data Binding

This app uses data binding to reuse components.

Data binding is enabled in the app `build.gradle` file.

```groovy
    dataBinding {
        enabled true
    }
```

Fragment layout content is encapsulated in the `<layout>` root element

```xml
<layout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:tools="http://schemas.android.com/tools"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    tools:context="com.continuouswave1550.fitness.fitnessprohome.qrscan.QRScanFragment">
    <androidx.constraintlayout.widget.ConstraintLayout
        android:layout_width="match_parent"
        android:layout_height="match_parent">

        <!-- TODO: Update blank fragment layout -->
    

    </androidx.constraintlayout.widget.ConstraintLayout>
</layout>
```

### Navigation

This application uses the Jetpack navigation libraries to implement navigation functionality in the app.

#### Navigate Up

In order to implement navigation using the navigate up icon in the upper left corner of the app, override the `onSupportNavigateUp()` function.

Get an instance oif the navController and call the `navigateUp()` function.

```kotlin
    override fun onSupportNavigateUp(): Boolean {
        super.onSupportNavigateUp()
        val navController = this.findNavController(R.id.navigationHostFragment)
        return navController.navigateUp()
    }
```

## References
- [Build a Flexible UI](https://developer.android.com/training/basics/fragments/fragment-ui)
- [android kotlin - Fragment transaction example](https://android--examples.blogspot.com/2019/07/android-kotlin-fragment-transaction.html)
- [Get started with the Navigation component](https://developer.android.com/guide/navigation/navigation-getting-started)
- [Testing on Android using JUnit 5](https://www.lordcodes.com/articles/testing-on-android-using-junit-5)
- [CameraX Overview](https://developer.android.com/training/camerax)
- [Getting Started with CameraX](https://codelabs.developers.google.com/codelabs/camerax-getting-started/index.html?index=..%2F..index#0)