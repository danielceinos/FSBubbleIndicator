# FSBubbleIndicator

[![License](https://img.shields.io/badge/license-MIT-blue.svg)](https://github.com/danielceinos/FSBubbleIndicator/blob/master/LICENSE.md)
[![Version](https://img.shields.io/badge/jitpack-1.0.0-green.svg)](https://jitpack.io/#danielceinos/FSBubbleIndicator/1.0.0)

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
	        compile 'com.github.danielceinos:FSBubbleIndicator:1.0.0'
	}
  ```
# Use

## Options

```xml
    <attr format="dimension" name="textSize" />
    <attr format="color" name="bubbleColor" />
    <attr name="textColor" format="color"/>
    <attr format="integer" name="count" />
    <attr name="shadowColor" format="color"/>
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

```kotlin
	findViewById<FSBubbleIndicator>(R.id.bubble).count = 777
```