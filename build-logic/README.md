## build_logic

`build-logic` 디렉토리내 에서는 각 모듈들에 필요한 공통 설정들에 대해 정의된 모듈이다. 
이 모듈 에서는 다른 메인, 서브 모듈 들 에서 공통 되는 내용들의 보일러 플레이트화 를 피하고 공통화 하여 빌드 스크립트의 중복화를 막는다.

각 서브 모듈에서 플러그인을 추가 하려면 아래 처럼 `build.gradle.kts` 의 `plugins` 항목에 추가 하면 된다. 

```
plugins {
    id("kr.swkang.playground.android.application")
    id("kr.swkang.playground.android.application.compose")
    // ...
}
```

이 프로젝트에서 구성된 플러그인의 목록은 아래와 같다. 

- 모든 모듈에 적용 가능
  - `kr.swkang.playground.android.application` : 안드로이드 기본 구성
  - `kr.swkang.playground.android.application.compose` : 컴포즈 관련 구성
  - `kr.swkang.playground.android.feature` : 안드로이드 피쳐 들
  - `kr.swkang.playground.android.hilt` : DI 도구인 Hilt 사용을 위한 구성
  - `kr.swkang.playground.android.test`
- 라이브러리 모듈 
  - `kr.swkang.playground.android.library` : 라이브러리 모듈에 적용하기 위한 안드로이드 구성
  - `kr.swkang.playground.android.library.compose` : 라이브러리 모듈에 적용하기 위한 컴포즈
  

### 참고

  - [Herding Elephants / Wrangling a 3,500-module Gradle project](https://developer.squareup.com/blog/herding-elephants/)
  - [idiomatic-gradle - gitHub](https://github.com/jjohannes/idiomatic-gradle)