package kr.swkang.common.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kr.swkang.common.DefaultDispatcher
import kr.swkang.common.IoDispatcher
import kr.swkang.common.MainDispatcher
import kr.swkang.common.MainImmediateDispatcher

/**
 * 코루틴 디스패처를 주입 받아서 쓸 수 있게 도와주는 (싱글턴)모듈.
 * 아래 예제 처럼 각 디스패처 에 정의된 `Qualfier` 을 사용 하여 쓰면 된다.
 *
 *   - 퀄리파이어로 정의된 디스패처를 주입 받는 방법
 *   ```kotlin
 *   @Singleton
 *   @Provide
 *   fun provideApplicationCoroutineScope(
 *       @DefaultDispatcher defaultDispatcher: CoroutineDispatcher
 *   ): CoroutineScope = CoroutineScope(SupervisorJob() + defaultDispatcher)
 *   ```
 *
 *   - `withContext()`를 이용해 suspend 함수를 지정한 코루틴 내 에서 동작 시킬 때 사용 한다.
 *   ```kotlin
 *   class SomeRepositoryImpl(
 *       @DefaultDispatcher val dispatcher: CoroutineDispatcher
 *   ): SomeRepository {
 *       suspend fun requestSession() withContext(dispatcher) {
 *           // ...
 *       }
 *   }
 *   ```
 *
 * @author bmo
 * @since 2023-03-11
 */
@InstallIn(SingletonComponent::class)
@Module
object DispatchersModule {
    @Provides
    @DefaultDispatcher
    fun provideDefaultDispatcher(): CoroutineDispatcher = Dispatchers.Default

    @Provides
    @IoDispatcher
    fun provideIoDispatcher(): CoroutineDispatcher = Dispatchers.IO

    @Provides
    @MainDispatcher
    fun provideMainDispatcher(): CoroutineDispatcher = Dispatchers.Main

    @Provides
    @MainImmediateDispatcher
    fun provideMainImmediateDispatcher(): CoroutineDispatcher = Dispatchers.Main.immediate
}
