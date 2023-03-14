# PlayGround

WIP..

## 1. basic

- kotlin
- mvvm
- [hilt](https://dagger.dev/hilt/)
- compose
  - [compose-lint](https://slackhq.github.io/compose-lints/)
- multi-module
- coroutine 
- gitHub action
- [ktlint](https://pinterest.github.io/ktlint/)
- [detekt](https://github.com/detekt/detekt)
- [secrets](https://developers.google.com/maps/documentation/android-sdk/secrets-gradle-plugin?hl=ko)

## 2. modules

### 2.1 `app`

메인 화면. 

### 2.2 `core`

- `common` : (LIB) 공통
  - Main, IO 등 주입받아서 사용 할 수 있게 한 코루틴 디스패처.
- `design` : (LIB) 머티리얼 디자인 테마, 컬러, 위젯 컴포넌트 등 컴포즈에 사용될 리소스
  - 커스텀 뷰 는 컴포즈로만 작성 하며 이 서브 모듈 에서만 추가 한다.
  - 추가되는 컬러 등은 internal 으로 모듈 내 에서만 사용 가능 하다.
- `network` : (LIB) Retrofit, OkHttp 등 공통 코드.
- `test` : (LIB) 테스트 코드 공통, 러너 등.

### 2.3 [`build-logic`](./build-logic/README.md) 

- convention : 멀티 모듈 환경에서 각 메인, 서브 모듈들의 `build.gradle.kts` 에 적용 되는 중복(보일러 플레이트)되는 코드들을 따로 정리 하여 공통 일괄 적용 시켜준다. 
 
### 2.4 `feature`

실제로 구현될 피쳐 레이어.

- pokemon : `poke` api 를 활용한 간단한 포켓몬 목록, 상세 보기.

서브 feature 의 구현

## 3. CI, Code quality

- GitHub Action
- ktlint
- detekt

## 4. Unit test

- turbine