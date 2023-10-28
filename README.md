# JDK-Monitor
A simple command line utility to monitor jdk releases.
# Usage
```shell
Usage: jdkmon [-hV] [COMMAND]
JDK Monitor monitor the openjdk projects for newer releases.
  -h, --help      Show this help message and exit.
  -V, --version   Print version information and exit.
Commands:
  --features   List all the features targeted for the specific jdk version
               release.
  --schedules  List schedules(date) for the specific jdk version release.

```
# Examples
```shell
>./jdkmon
Fetching all the available JDKs... 
╔═════════╤══════════════════╗
║ Version │           Status ║
╠═════════╪══════════════════╣
║      22 │ (in development) ║
╟─────────┼──────────────────╢
║      21 │  (GA 2023/09/19) ║
╟─────────┼──────────────────╢
║      20 │  (GA 2023/03/21) ║
╟─────────┼──────────────────╢
║      19 │  (GA 2022/09/20) ║
╟─────────┼──────────────────╢
║      18 │  (GA 2022/03/22) ║
╟─────────┼──────────────────╢
║      17 │  (GA 2021/09/14) ║
╟─────────┼──────────────────╢
║      16 │  (GA 2021/03/16) ║
╟─────────┼──────────────────╢
║      15 │  (GA 2020/09/15) ║
╟─────────┼──────────────────╢
║      14 │  (GA 2020/03/17) ║
╟─────────┼──────────────────╢
║      13 │  (GA 2019/09/17) ║
╟─────────┼──────────────────╢
║      12 │  (GA 2019/03/19) ║
╟─────────┼──────────────────╢
║      11 │  (GA 2018/09/25) ║
╟─────────┼──────────────────╢
║      10 │  (GA 2018/03/20) ║
╚═════════╧══════════════════╝

>./jdkmon --schedules 22
╔════════════╤══════════════════════════════════════════╗
║       Date │                              Description ║
╠════════════╪══════════════════════════════════════════╣
║ 2023/12/07 │ Rampdown Phase One (fork from main line) ║
╟────────────┼──────────────────────────────────────────╢
║ 2024/01/18 │                       Rampdown Phase Two ║
╟────────────┼──────────────────────────────────────────╢
║ 2024/02/08 │                Initial Release Candidate ║
╟────────────┼──────────────────────────────────────────╢
║ 2024/02/22 │                  Final Release Candidate ║
╟────────────┼──────────────────────────────────────────╢
║ 2024/03/19 │                     General Availability ║
╚════════════╧══════════════════════════════════════════╝

>./jdkmon --features 22 
╔══════╤═══════════════════════════════════════════╗
║ Jeps │                               Description ║
╠══════╪═══════════════════════════════════════════╣
║  460 │ Vector API (Seventh Incubator) 2023/11/03 ║
╟──────┼───────────────────────────────────────────╢
║  454 │             Foreign Function & Memory API ║
╟──────┼───────────────────────────────────────────╢
║  456 │              Unnamed Variables & Patterns ║
╚══════╧═══════════════════════════════════════════╝

```
# Development
This Project uses core java with library like [Picocli](https://picocli.info/), [Jsoup](https://jsoup.org/), [AsciiTable](https://github.com/freva/ascii-table)

To build the project, make sure to the following things are installed:
* Java 21 or newer
* GraalVM 21+35.1 or newer
* Maven 3.9.3 or newer

The following build commands are commonly used:
```shell
 ./mvn clean package
```
# Creating a Native Executable
You can create a native executable using:
```shell
./mvn package -Pnative
```
# License
This code base is available under the Apache License, version 2.