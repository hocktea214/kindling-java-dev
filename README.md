# kindling-java
Kindling-java is an attach agent to collect Java CPU / LOCK for probe which relies on async-profiler.

## HowTo Build
* Build Kindling Java

> mvn clean package

* Copy Kindling Java To AsyncProfiler

```
# Agent is built in agent-package/target/agent-package-<version>.zip
$ cd agent-package/target
$ unzip agent-package-1.0.0.zip
$ cp -R agent-package <asyncProfiler Path>/agent
```

* Build AsyncProfiler

```
$ make release
$ tar -zxvf async-profiler-2.8.3-linux-x64.tar.gz

async-profiler-2.8.3-linux-x64
├── agent
│   ├── 1.0.0
│   │   ├── agent-core.jar
│   │   └── plugin-traceid-sw.jar
│   ├── agent-boot.jar
│   └── version
├── build
│   ├── async-profiler.jar
│   ├── converter.jar
│   ├── fdtransfer
│   ├── jattach
│   └── libasyncProfiler.so
├── CHANGELOG.md
├── LICENSE
├── profiler.sh
└── README.md
```

## HowTo Use
* Attach Java

> ./profiler.sh start $pid

* Detach Java

> ./profiler.sh stop $pid