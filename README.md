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
    tools:context="com.continuouswave1550.fitness.fitnessprohome.myfitness.MyFitnessFragmen">
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

#### Navigation Drawer
This app includes a navigation drawer that can be removed in a branch or fork of this code.  Material Design is used in the navigation drawer.

Add a Material Design dependency in the app `build.gradle` file.
```groovy
dependencies {
    ...
    implementation "com.google.android.material:material:$supportlibVersion"
    ...
}
```

A menu resource is used for the navigation drawer.

```xml
<?xml version="1.0" encoding="utf-8"?>
<menu xmlns:app="http://schemas.android.com/apk/res-auto"
    xmlns:android="http://schemas.android.com/apk/res/android">

    <item
        android:id="@+id/aboutFragment"
        android:icon="@drawable/dashboard_24px"
        android:title="@string/about" />
    <item
        android:id="@+id/settingsFragment"
        android:icon="@drawable/settings_24px"
        android:title="@string/settings" />
</menu>
```

The `androidx.drawerlayout.widget.DrawerLayout` element should be the first child of the `layout` element.  Add the navigation drawer to the `activity_main.xml` layout.

```xml
<?xml version="1.0" encoding="utf-8"?>
<layout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    xmlns:tools="http://schemas.android.com/tools"
    tools:context="com.continuouswave1550.fitness.fitnessprohome.MainActivity">
    <androidx.drawerlayout.widget.DrawerLayout
        android:id="@+id/mainContainer"
        android:layout_width="match_parent"
        android:layout_height="match_parent">

        <LinearLayout
            android:layout_width="match_parent"
            android:layout_height="match_parent"
            android:orientation="vertical">

            <fragment
                android:id="@+id/navigationHostFragment"
                android:name="androidx.navigation.fragment.NavHostFragment"
                android:layout_width="match_parent"
                android:layout_height="match_parent"
                app:defaultNavHost="true"
            app:navGraph="@navigation/nav_graph" />

            <com.google.android.material.bottomnavigation.BottomNavigationView
                            android:id="@+id/bottomNavigation"
                            android:layout_width="match_parent"
                            android:layout_height="wrap_content"
                            app:menu="@menu/bottom_navigation_menu"/>
        </LinearLayout>
        ...
        ...
        <com.google.android.material.navigation.NavigationView
            android:id="@+id/navigationDrawerLayout"
            android:layout_height="match_parent"
            android:layout_width="wrap_content"
            android:layout_gravity="start"
            android:menu="@menu/nav_drawer_menu"/>

    </androidx.drawerlayout.widget.DrawerLayout>
</layout>
```

Add the navigation drawer to the activity class.

```kotlin
...
import androidx.drawerlayout.widget.DrawerLayout
...
class MainActivity : AppCompatActivity(){
    private lateinit var binding : ActivityMainBinding
    private lateinit var drawerLayout: DrawerLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        ...
        ...
        drawerLayout = binding.drawerLayout
        NavigationUI.setupActionBarWithNavController(this, navController, drawerLayout)
        NavigationUI.setupWithNavController(binding.navigationDrawerLayout, navController)
        ...
    }
    override fun onSupportNavigateUp(): Boolean {
        super.onSupportNavigateUp()
        val navController = this.findNavController(R.id.navigationHostFragment)
        return NavigationUI.navigateUp(navController, drawerLayout)
    }

    ...
```

#### Bottom Navigation
The bottom navigation bar is where icons for quick links live.  The content of the bottom navigation bar is defined in a menu resource like the one listed below:

**`bottom_navigation_menu.xml`**

```xml
<?xml version="1.0" encoding="utf-8"?>
<menu xmlns:android="http://schemas.android.com/apk/res/android">
    <item
        android:id="@+id/homeFragment"
        android:enabled="true"
        android:icon="@drawable/home_24px"
        android:title="@string/home_nav_title"/>
    <item
        android:id="@+id/strenthFragment"
        android:enabled="true"
        android:icon="@drawable/strength_24px"
        android:title="@string/exercise_nav_title"/>
    <item
        android:id="@+id/flexibilityFragment"
        android:enabled="true"
        android:icon="@drawable/flexibility_24px"
        android:title="@string/flexibility_nav_title"/>
    <item
        android:id="@+id/dietFragment"
        android:enabled="true"
        android:icon="@drawable/diet_24px"
        android:title="@string/diet_nav_title"/>
</menu>
```



We use the `com.google.android.material.bottomnavigation.BottomNavigationView` element for the bottom navigation inside of the `LinearLayout` just below the `NavHostFragment`

```xml
<layout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    xmlns:tools="http://schemas.android.com/tools"
    tools:context="com.continuouswave1550.fitness.fitnessprohome.MainActivity">
    <androidx.drawerlayout.widget.DrawerLayout
        android:id="@+id/drawerLayout"
        android:layout_width="match_parent"
        android:layout_height="match_parent">

        <LinearLayout
            android:layout_width="match_parent"
            android:layout_height="match_parent"
            android:orientation="vertical">

            <fragment
                android:id="@+id/navigationHostFragment"
                android:name="androidx.navigation.fragment.NavHostFragment"
                android:layout_width="match_parent"
                android:layout_height="0dp"
                android:layout_weight="1"
                app:defaultNavHost="true"
                app:navGraph="@navigation/nav_graph" />

            <com.google.android.material.bottomnavigation.BottomNavigationView
                android:id="@+id/bottomNavigation"
                android:layout_width="match_parent"
                android:layout_height="wrap_content"
                app:menu="@menu/bottom_navigation_menu"/>
        </LinearLayout>
        ...
        ...
    </androidx.drawerlayout.widget.DrawerLayout>
</layout>
```

This is called in the `MainActivity` class via a private function.

1. Setup the bottom navigation with the `navController`
2. Set the navigation item listener to respond when a navigation item is selected.

```kotlin
...
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView<ActivityMainBinding>(this, R.layout.activity_main)
        val navController = this.findNavController(R.id.navigationHostFragment)
        drawerLayout = binding.drawerLayout
        NavigationUI.setupActionBarWithNavController(this, navController, drawerLayout)
        NavigationUI.setupWithNavController(binding.navigationDrawerLayout, navController)

        setupBottomNavMenu(navController)
    }
    ...
    ...
    private fun setupBottomNavMenu(navController: NavController) {
        val bottomNav: BottomNavigationView = binding.bottomNavigation
        bottomNav?.setupWithNavController(navController)
        bottomNav.setOnNavigationItemReselectedListener { item ->
            NavigationUI.onNavDestinationSelected(item, navController)
        }
    }
```

### Menus

#### Options Menu
To add an options menu to a fragment, call the `setHasOptionsMenu(true)` function in the fragment's `onCreateView` function.

```kotlin
...
...
override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        ...
        setHasOptionsMenu(true)
        ,,,
        ...
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        ...
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return super.onOptionsItemSelected(item)
        ...
    }
```

### Data Persistence

Data persistence in an Android application can be complex.  Below is a summary of the data management and persistence components of this app.

- ViewModel class
- Entity class
- DAO class
- Abstract Database class

**Abstract Database Class**

```kotlin
import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [Exercise::class], version = 1, exportSchema = false)
abstract class ExerciseDatabase : RoomDatabase() {}
```

-- Under Construction --

### Coroutines and Asynchronous Processes

Coroutines are an experimental feature of Kotlin and must be explicitly added in the project dependencies.

```groovy
dependencies {
    ...
    def coroutine_version = "1.3.8"
    ...
    implementation "org.jetbrains.kotlinx:kotlinx-coroutines-core:$coroutine_version"
    implementation "org.jetbrains.kotlinx:kotlinx-coroutines-android:$coroutine_version"
    ...
}
```

## Testing

-- Under Construction --

## References
- [Build a Flexible UI](https://developer.android.com/training/basics/fragments/fragment-ui)
- [android kotlin - Fragment transaction example](https://android--examples.blogspot.com/2019/07/android-kotlin-fragment-transaction.html)
- [Get started with the Navigation component](https://developer.android.com/guide/navigation/navigation-getting-started)
- [Jetpack Navigation](https://codelabs.developers.google.com/codelabs/android-navigation/index.html?index=..%2F..index#0)
- [Testing on Android using JUnit 5](https://www.lordcodes.com/articles/testing-on-android-using-junit-5)
- [CameraX Overview](https://developer.android.com/training/camerax)
- [Getting Started with CameraX](https://codelabs.developers.google.com/codelabs/camerax-getting-started/index.html?index=..%2F..index#0)
- [Jetpack Lifecycle](https://developer.android.com/jetpack/androidx/releases/lifecycle)
- [Room Persistence Library](https://developer.android.com/topic/libraries/architecture/room)