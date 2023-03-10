# PlayGround

WIP..

## 1. basic

kotlin, mvvm, compose, multi-module, kts,  

## 2. modules

### `core`

- common : 공통
- design : 머티리얼 디자인 테마, 컬러 등 컴포즈에 사용될 리소스
- network
- database
- data

### [`build-logic`](./build-logic/README.md) 

- convention : 멀티 모듈 환경에서 각 메인, 서브 모듈들의 `build.gradle.kts` 에 적용 되는 중복(보일러 플레이트)되는 코드들을 따로 정리 하여 공통 일괄 적용 시켜준다. 
 
### `domain`

서브 feature 의 구현

