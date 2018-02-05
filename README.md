# FSBubbleIndicator

[![License](https://img.shields.io/badge/license-MIT-blue.svg)](https://github.com/danielceinos/FSBubbleIndicator/blob/master/LICENSE.md)
[![Version](https://img.shields.io/badge/jitpack-1.0.1-green.svg)](https://jitpack.io/#danielceinos/FSBubbleIndicator/1.0.1)

<p align="center">
	<img src="https://github.com/danielceinos/FSBubbleIndicator/blob/develop/example.png" />
</p>

# Requirements

- minAndroidSdk: 16

# Installation
  
  Add to your gradle.build:
  ```
  allprojects {
		repositories {
			...
			maven { url 'https://jitpack.io' }
		}
	}
  ```
  ```
  dependencies {
	        compile 'com.github.danielceinos:FSBubbleIndicator:1.0.1'
	}
  ```
# Use

## Options

```xml
	<declare-styleable name="FSBubbleIndicator">
    	<attr format="dimension" name="textSize" />
    	<attr format="color" name="bubbleColor" />
    	<attr format="color" name="textColor" />
    	<attr format="integer" name="count" />
    	<attr format="color" name="shadowColor" />
  	</declare-styleable>

  	<declare-styleable name="FSIconBubble">
    	<attr format="reference" name="icon" />
  	</declare-styleable>

```

## Example

```xml
  <com.fireshield.library.FSBubbleIndicator
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        app:count="55555555"
        app:bubbleColor="#3c3eed"
        app:textSize="34sp"
        />
```

```xml
 <com.fireshield.library.FSIconBubble
        android:layout_width="48dp"
        android:layout_height="48dp"
        app:textSize="8sp"
        app:count="5"
        app:bubbleColor="#ba68c8"
        app:icon="@drawable/ic_favorite_border"
        />
```

```kotlin
	findViewById<FSBubbleIndicator>(R.id.bubble).count = 777
```