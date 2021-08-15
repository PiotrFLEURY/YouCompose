# YouCompose

A Youtube video listing app made with Jetpack Compose

## Jetpack Compose

For more information about Jetpack Compose please consult the official documentation [here](https://developer.android.com/jetpack/compose/documentation)

## Youtube API

For more information about Youtube API please consult the official documentation [here](https://developers.google.com/youtube/v3/docs)

## API Key

This application required an API key to work.

You can generate your own key by following Youtube API instructions [here](https://developers.google.com/youtube/v3/docs#calling-the-api)

Once created, add your API key in the file `src/main/res/values/keys.xml` like so

```XML
<?xml version="1.0" encoding="utf-8"?>
<resources>
    <string name="YOUTUBE_API_KEY" translatable="false">[YOUR_API_KEY_HERE]</string>
</resources>
```