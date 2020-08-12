Sample project to reproduce mockk/robolectric incompatibility
=============================================================

Steps to reproduce:
------------------
* Run `./gradlew testDebugUnitTest`
* Expected behavior: tests pass
* Actual behavior: `testMockk2()` fails:
    ```
    io.mockk.MockKException: Can't instantiate proxy for class com.example.robolectricmockk.Foo
    	at io.mockk.impl.instantiation.JvmMockFactory.newProxy(JvmMockFactory.kt:64)
    	at io.mockk.impl.instantiation.AbstractMockFactory.newProxy$default(AbstractMockFactory.kt:29)
    	at io.mockk.impl.instantiation.AbstractMockFactory.mockk(AbstractMockFactory.kt:59)
    	at com.example.robolectricmockk.ExampleUnitTest.setUp(ExampleUnitTest.kt:53)
    	at sun.reflect.NativeMethodAccessorImpl.invoke0(Native Method)
    	at sun.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:62)
    	at sun.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)
    	at java.lang.reflect.Method.invoke(Method.java:498)
    	at org.junit.runners.model.FrameworkMethod$1.runReflectiveCall(FrameworkMethod.java:50)
    	at org.junit.internal.runners.model.ReflectiveCallable.run(ReflectiveCallable.java:12)
    	at org.junit.runners.model.FrameworkMethod.invokeExplosively(FrameworkMethod.java:47)
    	at org.junit.internal.runners.statements.RunBefores.evaluate(RunBefores.java:24)
    	at org.robolectric.RobolectricTestRunner$HelperTestRunner$1.evaluate(RobolectricTestRunner.java:575)
    	at org.robolectric.internal.SandboxTestRunner$2.lambda$evaluate$0(SandboxTestRunner.java:263)
    	at org.robolectric.internal.bytecode.Sandbox.lambda$runOnMainThread$0(Sandbox.java:89)
    	at java.util.concurrent.FutureTask.run(FutureTask.java:266)
    	at java.util.concurrent.ThreadPoolExecutor.runWorker(ThreadPoolExecutor.java:1149)
    	at java.util.concurrent.ThreadPoolExecutor$Worker.run(ThreadPoolExecutor.java:624)
    	at java.lang.Thread.run(Thread.java:748)
    Caused by: io.mockk.proxy.MockKAgentException: Instantiation exception
    	at io.mockk.proxy.jvm.ProxyMaker.proxy(ProxyMaker.kt:54)
    	at io.mockk.impl.instantiation.JvmMockFactory.newProxy(JvmMockFactory.kt:34)
    	... 18 more
    Caused by: java.lang.ClassCastException: Cannot cast com.example.robolectricmockk.Foo to com.example.robolectricmockk.Foo
    	at java.lang.Class.cast(Class.java:3369)
    	at io.mockk.proxy.jvm.ObjenesisInstantiator.instanceViaObjenesis(ObjenesisInstantiator.kt:73)
    	at io.mockk.proxy.jvm.ObjenesisInstantiator.instance(ObjenesisInstantiator.kt:42)
    	at io.mockk.proxy.jvm.ProxyMaker.instantiate(ProxyMaker.kt:75)
    	at io.mockk.proxy.jvm.ProxyMaker.proxy(ProxyMaker.kt:42)
    	... 19 more
    ```

Additional information:
-----------------------
* Same behavior is observed using java 8 or java 14
* Both tests pass using robolectric 4.3.1 and java 8
