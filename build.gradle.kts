import org.jetbrains.gradle.ext.CopyrightConfiguration
import org.jetbrains.gradle.ext.ProjectSettings

import java.time.LocalDate

plugins {
    java

    id("org.jetbrains.gradle.plugin.idea-ext") version "0.5"
}

group = "com.typicalbot.ext"
version = Version(major = "1", minor = "0", patch = "0").toString()

java {
    sourceCompatibility = JavaVersion.VERSION_1_8
    targetCompatibility = JavaVersion.VERSION_1_8
}

repositories {
    jcenter()
}

dependencies {
    implementation("com.typicalbot:typicalbot:3.0.0")
    testCompile("junit", "junit", "4.12")
}

tasks.withType<JavaCompile> {
    options.encoding = "UTF-8"
    options.isIncremental = true
    options.compilerArgs = mutableListOf("-Xlint:deprecation", "-Xlint:unchecked")
}

idea {
    project {
        this as ExtensionAware
        configure<ProjectSettings> {
            this as ExtensionAware
            configure<CopyrightConfiguration> {
                profiles {
                    create("TypicalBot License") {
                        keyword = "Copyright"
                        notice = """
                            Copyright ${LocalDate.now().year} Bryan Pikaard, Nicholas Sylke and the TypicalBot contributors
                            
                            Licensed under the Apache License, Version 2.0 (the "License");
                            you may not use this file except in compliance with the License.
                            You may obtain a copy of the License at
                            
                                 http://www.apache.org/licenses/LICENSE-2.0
                                 
                            Unless required by applicable law or agreed to in writing, software
                            distributed under the License is distributed on an "AS IS" BASIS,
                            WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
                            See the License for the specific language governing permissions and
                            limitations under the License.
                        """.trimIndent()
                    }
                }
            }
        }
    }
}

class Version(val major: String, val minor: String, val patch: String) {
    override fun toString() = "$major.$minor.$patch"
}
