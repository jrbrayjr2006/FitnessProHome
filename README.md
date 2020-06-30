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

Add a binding property to the Activity or/and Fragment.

```kotlin
...
class MyFitnessFragment : Fragment() {
    private lateinit var binding : FragmentMyFitnessBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_my_fitness, container,false)
        binding.scanButton.setOnClickListener { view: View ->
            view.findNavController().navigate(R.id.action_myFitnessFragment_to_QRScanFragment)
        }
        setHasOptionsMenu(true)
        return binding.root
    }
    ...
    ...
```

### Navigation

This application uses the Jetpack navigation libraries to implement navigation functionality in the app.

#### Navigation Host

Add a navigation host fragment to the Activity layout.  This acts as a host for navigation items in the navigation graph.

```xml
        <fragment
            android:id="@+id/navigationHostFragment"
            android:name="androidx.navigation.fragment.NavHostFragment"
            android:layout_width="match_parent"
            android:layout_height="match_parent"
            app:defaultNavHost="true"
            app:navGraph="@navigation/nav_graph" />
```

#### Navigation Graph

Screens can be visually added to the navigation graph in design mode.  The navigation graph is a navigation resource that exists in the `res/navigation/` directory of the project.

```xml
<?xml version="1.0" encoding="utf-8"?>
<navigation xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    xmlns:tools="http://schemas.android.com/tools"
    android:id="@+id/nav_graph"
    app:startDestination="@id/homeFragment">

    ...
    <fragment
        android:id="@+id/QRScanFragment"
        android:name="com.continuouswave1550.fitness.fitnessprohome.qrscan.QRScanFragment"
        android:label="fragment_q_r_scan"
        tools:layout="@layout/fragment_q_r_scan" />
        ...
</navigation>
```

#### Navigate Up

In order to implement navigation using the navigate up icon in the upper left corner of the app, override the `onSupportNavigateUp()` function in the main Activity.

Get an instance of the navController and call the `navigateUp()` function.

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