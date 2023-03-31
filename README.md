# PlayGround

Toy Project.

## 1. basic

- clean architecture
  - multi-module
- coroutine
- kotlin
- mvvm
- [material design 3](https://m3.material.io/)
- androidx
  - navigation
  - lifecycle
  - paging3
  - compose
    - [compose-lint](https://slackhq.github.io/compose-lints/)
    - [accompanist](https://github.com/google/accompanist)
- [hilt](https://dagger.dev/hilt/)
- [coil](https://coil-kt.github.io/coil/)
- (WIP) kotlin-serializable
- ci/cd, code quality
  - [gitHub action](https://github.com/ksu3101/PlayGround/actions)
  - [ktlint](https://pinterest.github.io/ktlint/)
  - [detekt](https://github.com/detekt/detekt)
  - [secrets](https://developers.google.com/maps/documentation/android-sdk/secrets-gradle-plugin?hl=ko)
- unit test
  - [turbine](https://github.com/cashapp/turbine) 
  - [mockk](https://mockk.io/ANDROID.html)

## 2. modules

### 2.1 `app`

메인 화면.

- `@Composable PlayGroundMain()` : 메인 화면의 기본 베이스, 뷰 와 네비게이션 호스트 연결.
  - `PlayGroundNavHost` : 앱 네비게이션 Host 
  - `@Composable MainScreen()` : 메인 화면 뷰

### 2.2 `core`

- `common` : (LIB) 공통
  - Main, IO 등 주입받아서 사용 할 수 있게 한 코루틴 디스패처.
- `data` : (LIB) 데이터 레이어
  - (Repository, DataSource) 네트워크, 로컬 데이터 소스 에 대한 비즈니스 로직 정의.  
    - 비즈니스 로직이 너무 방대해지면 이를 `domain` 모듈에 UseCase 로 쪼갠다.
- `domain` : (LIB) 도메인 레이어 
  - 각 피쳐에서 사용 하게 될 UseCase. 
- `design` : (LIB) 머티리얼 디자인 테마, 컬러, 위젯 컴포넌트 등 컴포즈에 사용될 리소스
  - 커스텀 뷰 는 컴포즈로만 작성 하며 이 서브 모듈 에서만 추가 한다.
  - 추가되는 컬러 등은 internal 으로 모듈 내 에서만 사용 가능 하다.
  - 참고 [Material Theme Builder](https://m3.material.io/theme-builder#/custom)
- `network` : (LIB) Retrofit, OkHttp 등 공통 코드.
- `test` : (LIB) 테스트 코드 공통, 러너 등.

### 2.3 [`build-logic`](./build-logic/README.md) 

- convention : 멀티 모듈 환경에서 각 메인, 서브 모듈들의 `build.gradle.kts` 에 적용 되는 중복(보일러 플레이트)되는 코드들을 따로 정리 하여 공통 일괄 적용 시켜준다.
  - 아래에 정의 된 각 플러그인 들은 필요에 따라 추가 하면 된다.
    - 모든 모듈에 적용 가능
      - `kr.swkang.playground.android.application` : 안드로이드 기본 구성
      - `kr.swkang.playground.android.application.compose` : 컴포즈 관련 구성
      - `kr.swkang.playground.android.feature` : 안드로이드 피쳐 들
      - `kr.swkang.playground.android.hilt` : DI 도구인 Hilt 사용을 위한 구성
      - `kr.swkang.playground.android.test` : 테스트 공통 설정
    - 라이브러리 모듈
      - `kr.swkang.playground.android.library` : 라이브러리 모듈에 적용하기 위한 안드로이드 구성
      - `kr.swkang.playground.android.library.compose` : 라이브러리 모듈에 적용하기 위한 컴포즈
 
### 2.4 `feature`

실제로 구현될 서브 피쳐의 ui 레이어 구현 레이어. (Compose screen, ViewModel, navigation..)

- `pokemon` : [poke api](https://pokeapi.co/) 를 활용한 간단한 포켓몬 목록, 상세 보기.
  - svg [포켓몬 이미지 리소스](https://github.com/PokeAPI/sprites) 추가. 
  - 원래 이미지 서버를 두고 불러와야 하지만, 서버가 없어서 로컬에서 불러옴. 용량 이슈로 인하여 최대 300개 까지만 저장. 

## 3. CI/CD, Code quality

- GitHub Action
  - pre-commit : `.github/pre-commit` 스크립트를 `.git/hooks/pre-commit`으로 복사 하는 스크립트 `githooks.gradle.kts`를 실행 해야 한다.  
- ktlint
  - 이전의 플러그인 디펜던시 의존이 아닌 ktlint CLI를 이용하여 code style로 적용 되었다.  
  - 윈도우의 경우 [ktlint](https://github.com/pinterest/ktlint/releases) 릴리즈 jar 파일을 환경 변수에 등록 해야 한다. 이전 인터넷에 있던 `--apply-idea-to..`와 같은 명령어는 제거 되어 동작하지 않는다. 
- detekt
  - (CLI) `> detekt -r html:build/reports/detekt.html ...` : `-r` 파라미터 추가 한 뒤 필요한 리포트 파일과 경로를 추가 하면 된다

## 4. Unit test

- turbine
- mockk

## References

- [Now in android](https://github.com/android/nowinandroid)
