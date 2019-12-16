[![MIT Licence](https://badges.frapsoft.com/os/mit/mit.svg?v=103)](https://opensource.org/licenses/mit-license.php)
[![Open Source Love](https://badges.frapsoft.com/os/v1/open-source.svg?v=103)](https://github.com/ellerbrock/open-source-badges/)
# todo app - Clean Architecture

The purpose of this project is to develop a minimalist todo app with the aim to learn and practice the concepts of Clean Architecture  with MVP (for the presentation layer) using architecture components library like RxJava2, Dagger2 and ButterKnife.


![alt text](https://raw.githubusercontent.com/ejupialked/todo-clean-architecture/master/art/architecture.png)


#### Presentation Layer
This layer contains everything related to the UI, nothing else. You can use any pattern here, in this project I used MVP, this will ensure to separate your business logic from the UI.
#### Domain Layer
This is the place where all the magic happens, here you can find the entities and use cases of the app. The code written in this layer does not have any Android dependency, it is just Java. 
#### Data layer
This is the layer which takes care about all the data that our application needs. It can be provided by a database, network or memory (it can be anything, in the code I provide hardcoded data). The repository pattern allows to abstract the origin of data where it is not going to matter where the data comes from, the important thing is that they will be obtained from somewhere and we can use them to do the actions that have to be done.

## Project Status
This project has not been completed yet.
## Screenshots

![alt text](https://raw.githubusercontent.com/EjupiAlked/todo-app/master/art/tasktype_art.png)
![alt text](https://raw.githubusercontent.com/EjupiAlked/todo-app/master/art/tasks_art.png)

## Libraries used
* [AppCompat, CardView, RecyclerView and DesignLibrary][2]
* [RxJava & RxAndroid][3]
* [Dagger 2][4]
* [Butter Knife][5]
* [Recycler View Swipe Decorator][6]

[2]: http://developer.android.com/intl/es/tools/support-library/index.html
[3]: https://github.com/ReactiveX/RxAndroid
[4]: https://github.com/google/dagger
[5]: https://github.com/JakeWharton/butterknife
[6]: https://github.com/xabaras/RecyclerViewSwipeDecorator
[7]: https://github.com/EjupiAlked/TodoQuick/blob/master/LICENSE



## Contributing

Contributions are what make the open source community such an amazing place to be learn, inspire, and create. Any contributions you make are **greatly appreciated**.

1. Fork the Project
2. Create your Feature Branch (`git checkout -b feature/AmazingFeature`)
3. Commit your Changes (`git commit -m 'Add some AmazingFeature'`)
4. Push to the Branch (`git push origin feature/AmazingFeature`)
5. Open a Pull Request
## License

This project is licensed under the MIT License - see the [LICENSE.md][7] file for details.

## Developed by

Alked Ejupi - ejupialkedcs@gmail.com
