# PlayGround (WIP)

이 프로젝트는 토이 프로젝트로서 업무외 시간에 개인적인 목표를 잡고 여러가지의 도메인을 시도해 본다.

## 1. 기본

- Clean architecture
  - 목적에 따라 분리된 레이어를 모듈 단위로 완전히 분리 하여 벽을 세워준다.
  - `app` 모듈 : View, DI, Network등 플랫폼에 의존이 있는 클래스, 함수, 인터페이스등의 구현이 존재 하는 모듈
  - `common` 모듈 : app, model모듈에서 필요한 상수, 유틸리티, 확장 함수, String 자원 등 이 존재 하는 모듈
  - `moddel` 모듈 : ViewModel, Repository, Helper 인터페이스 와 비즈니스 로직이 존재 하는 모듈
- MVVM & Databinding
- ViewBinding
  - 간단한 view와 비즈니스 로직이 전무한 경우 뷰 바인딩만 사용 한다.
- [Redux event handler](https://github.com/ksu3101/TIL/blob/master/ETC/200305.md)
  - 전역적인 이벤트를 핸들링 하거나 domain간 의 이벤트를 전달 및 핸들링 해야할 경우 Redux방식의 이벤트 핸들링을 해당 도메인에 적용 한다.


- 목표
  - 기술에 대한 경험을 얻기 위해 큰 목표 보다는 작은 목표 단위로 잘게 쪼개고 좋은 코드를 개발하기 위한 고민을 하고 적용 하며, 문제가 있어 진행이 어려우면 과감하게 결정 한다.
  - 단위 테스트 코드는 비즈니스 로직에 한해서 작성한다. 좋은 뷰 단위 테스트 도구가 생기면 테스트 하는 것 도 좋다.
  - 개인적으로 만들고 싶었던 것의 프로토타이핑을 이 토이 프로젝트에서 작성하여 테스트 해 볼 수 있다. 만약 프로토타이핑이 생각보다 괜찮거나 더 발전시키고 싶다면 이 프로젝트에서 분리하는 것도 좋다.

### 1.1 사용된 라이브러리

- [kotlin](https://mvnrepository.com/artifact/org.jetbrains.kotlin/kotlin-stdlib-jdk7)
- jetpack
  - [appcompat](https://developer.android.com/jetpack/androidx/releases/appcompat?hl=ko)
  - [recyclerView](https://developer.android.com/jetpack/androidx/releases/recyclerview?hl=ko)
  - [architecture lifecycle](https://developer.android.com/jetpack/androidx/releases/lifecycle?hl=ko)
  - [navigation](https://developer.android.com/jetpack/androidx/releases/navigation?hl=ko)
  - [paging](https://developer.android.com/jetpack/androidx/releases/paging?hl=ko)
  - [ktx core](https://developer.android.com/jetpack/androidx/releases/core?hl=ko)
  - [ktx-fragment](https://developer.android.com/jetpack/androidx/releases/fragment?hl=ko)
  - [constraintlayout](https://developer.android.com/jetpack/androidx/releases/constraintlayout?hl=ko)
- [multidex](https://developer.android.com/studio/build/multidex?hl=ko)
- [rxandroid](https://github.com/ReactiveX/RxAndroid)
- [dagger-hilt](https://dagger.dev/hilt/)
- [retrofit](https://square.github.io/retrofit/)
- [okhttp](https://square.github.io/okhttp/)
- [moshi](https://github.com/square/moshi)
- [glide](https://github.com/bumptech/glide)

## 2. 도메인

`MainActivity`에서 각 도메인으로 이동 할 수 있게 하고, 각 도메인은 서로간 의존을 자제 하거나 최소화 한다.

- MainActivity : 앱 실행시 진입 Activity

### 2.1 서브 도메인 1

(WIP)

### 2.2 서브 도메인 2

(WIP)

