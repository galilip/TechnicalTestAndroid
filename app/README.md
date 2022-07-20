# Technical android test
## Mobile App using newsapi to fetch & display french news
### -- https://newsapi.org/docs --

#### Tech & Libraries used
- Kotlin
- AndroidX Jetpack (Cardview, SwipeRefresh, etc.)
- Picasso (a Powerful image downloader and caching library for Android)
  https://square.github.io/picasso/
- Retrofit (A type-safe HTTP client for Android and Java)
  https://square.github.io/retrofit/
- JUnit, Mockito

I chose Picasso because library was easier and lighter than Glide
Furthermore I had already taken Retrofit for calling API, a framework by the same team

After spending time implementing a MVVM pattern with Coroutines I found out that I was not very
comfortable with it, so I've decided to remove it, and make things easier to me
MVVM pattern as below

model : News/Article/Source to represent datas
repository : NewsRepository, between my ViewModel & the API, it consume NewsDOC api through Retrofit
viewmodel : NewsViewModel, will store & manage my data persistance
MyViewModelFactory will handle creation of my viewmodel
view : NewsAdapter to be the link between data and the user interface, it will create child view
from parents. In my case it will create cardviews to display data in it

## API KEY STORAGE
after trying many things (Secret gradle plugin, local.properties, and others),
I found a simple solution, I've put my API KEYS into gradle.properties, then after
setting up a buildConfigField into my app/build.gradle
I was able to simply use BuildConfig.NEWSDOC_API_KEY to use it in my code

##### Unit Test
I was not very confident, didn't have opportunities to write with android
I tried, you may found the result in test folder


### Conclusion

I enjoyed spending time learning new things, trying stuff, again & again
I read a lot of documentations before starting, because there was new notions to learn
MVVM pattern, Coroutine, UnitTest, etc.
I made a try during first week, was able to fetch data & display it, but handling data with
coroutine & mvvm pattern was not very natural to me (Flow, DataSate, LiveData, etc.)
I was not satisfied, so I rewrite a large part with a simple approach within a new project that
you can see today.

You can launch the app, get all french news about sport displayed in a recyclerview through a
cardview with an image, a title, a desc & a date of publish.
You can click on a news, to display it in a dedicated activity.
It offers a larger image, more space, & you may visit the original website publisher by pressing
bottom button.

You can also vertical swipe with your finger to update news list.


This is not perfect, but it was made with passion ! I'm eager to learn new
things & cool stuff in mobile dev :)




