package com.example.fridgetrackerapp

import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.fridgetrackerapp.ui.theme.FridgeTrackerAppTheme

import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Assert.*
import org.junit.Rule

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */

@RunWith(AndroidJUnit4::class)
class MemTests {
    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun openWriteToMemTest() {
        composeTestRule.setContent {
            FridgeTrackerAppTheme {
                FridgeTrackerApp()
            }
        }
        composeTestRule.onNodeWithContentDescription("FAB").performClick()
        composeTestRule.onNodeWithContentDescription("Enter Manually").performClick()
        composeTestRule.onNodeWithText("Internal Storage in Android").assertExists()
    }

    @Test
    fun closeWriteToMemTest() {
        composeTestRule.setContent {
            FridgeTrackerAppTheme {
                FridgeTrackerApp()
            }
        }
        composeTestRule.onNodeWithContentDescription("FAB").performClick()
        composeTestRule.onNodeWithContentDescription("Enter Manually").performClick()
        composeTestRule.onNodeWithText("Internal Storage in Android").assertExists()
        composeTestRule.onNodeWithContentDescription("Scan Barcode").performClick()
        composeTestRule.onNodeWithText("Internal Storage in Android").assertDoesNotExist()
    }

    @Test
    fun writeToTextField() {
        composeTestRule.setContent {
            FridgeTrackerAppTheme {
                FridgeTrackerApp()
            }
        }
        composeTestRule.onNodeWithContentDescription("FAB").performClick()
        composeTestRule.onNodeWithContentDescription("Enter Manually").performClick()
        composeTestRule.onNodeWithText("Internal Storage in Android").assertExists()
        composeTestRule.onNodeWithTag("wordField").performTextInput("BANANA")
        composeTestRule.onNodeWithTag("wordField").assert(hasText("BANANA"))
    }

    @Test
    fun writeToTextFieldAndSaveEmptyString() {
        composeTestRule.setContent {
            FridgeTrackerAppTheme {
                FridgeTrackerApp()
            }
        }
        composeTestRule.onNodeWithContentDescription("FAB").performClick()
        composeTestRule.onNodeWithContentDescription("Enter Manually").performClick()
        composeTestRule.onNodeWithText("Internal Storage in Android").assertExists()
        composeTestRule.onNodeWithTag("wordField").performTextInput("")
        composeTestRule.onNodeWithTag("wordField").assert(hasText(""))

        composeTestRule.onNodeWithText("Write Data to Internal Storage").performClick()
        composeTestRule.onNodeWithText("Read Data from Internal Storage").performClick()
        composeTestRule.onNodeWithText("Data will appear below : \n").assertExists()

    }

    @Test
    fun writeToTextFieldAndSaveWord() {
        composeTestRule.setContent {
            FridgeTrackerAppTheme {
                FridgeTrackerApp()
            }
        }
        composeTestRule.onNodeWithContentDescription("FAB").performClick()
        composeTestRule.onNodeWithContentDescription("Enter Manually").performClick()
        composeTestRule.onNodeWithText("Internal Storage in Android").assertExists()
        composeTestRule.onNodeWithTag("wordField").performTextInput("BANANA")
        composeTestRule.onNodeWithTag("wordField").assert(hasText("BANANA"))

        composeTestRule.onNodeWithText("Write Data to Internal Storage").performClick()
        composeTestRule.onNodeWithText("Read Data from Internal Storage").performClick()
        composeTestRule.onNodeWithText("Data will appear below : \nBANANA").assertExists()

    }

    @Test
    fun writeToTextFieldAndSavePhrase() {
        composeTestRule.setContent {
            FridgeTrackerAppTheme {
                FridgeTrackerApp()
            }
        }

        val str = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Integer molestie ex at malesuada suscipit. Maecenas fermentum elit consequat nisl tristique varius. Proin commodo est at hendrerit congue. Aliquam sodales velit est, vel efficitur elit commodo eu. Nam volutpat blandit nibh, sit amet scelerisque erat cursus eget. Donec nec porta massa, nec imperdiet arcu. Vestibulum cursus varius dapibus. Integer a fermentum orci. Sed imperdiet dolor in mi pulvinar efficitur. Vivamus varius arcu ligula, sed ornare ex faucibus id. Donec in hendrerit risus, in volutpat tortor. Proin venenatis ipsum in tellus congue maximus. Suspendisse ullamcorper at tortor nec commodo. Fusce commodo nisl sed arcu hendrerit accumsan.\n" +
                "\n" +
                "In hac habitasse platea dictumst. Mauris elementum tortor quis dignissim vestibulum. Suspendisse nec urna semper, mollis purus nec, mollis ligula. Pellentesque aliquam quis ipsum non vestibulum. Nulla sed lacus sit amet ipsum pellentesque condimentum in et libero. Mauris a dignissim quam. Proin tristique sem malesuada molestie congue. Aliquam vel venenatis risus, finibus porttitor elit. Maecenas euismod nisi orci, et fermentum neque sagittis quis. Integer dapibus diam a ligula semper sollicitudin. Mauris hendrerit lacinia lobortis.\n" +
                "\n" +
                "Maecenas euismod fermentum egestas. Nam elementum porttitor ligula nec lacinia. Suspendisse rhoncus felis sed ante accumsan, ut pretium sapien luctus. Sed a ultrices nulla. Praesent porttitor leo nec orci placerat luctus. Suspendisse venenatis eget turpis et malesuada. Suspendisse potenti. Praesent consectetur, leo id fringilla commodo, sapien ex pulvinar purus, ut facilisis lacus turpis eu ligula. Aliquam vel leo id massa lacinia placerat in a sapien. Aenean at est augue. Suspendisse tincidunt urna in enim auctor, ut maximus nulla pretium. Ut sem turpis, fermentum ut efficitur at, fringilla a libero. Nunc sit amet malesuada velit, sed mollis leo. Donec ante nisl, ultrices at eros vel, accumsan porta dui. Ut consequat dignissim fringilla. Duis orci nisi, volutpat quis lacus ac, tempor pretium sem.\n" +
                "\n" +
                "Class aptent taciti sociosqu ad litora torquent per conubia nostra, per inceptos himenaeos. Aenean ornare sit amet velit id interdum. Maecenas mollis molestie libero eget aliquet. Pellentesque eget sem at tortor rutrum mattis. Pellentesque sollicitudin nec velit quis sodales. Donec lacinia justo odio, id suscipit ante ultrices id. Phasellus imperdiet leo nec nisi mattis, sed molestie urna sollicitudin."
        composeTestRule.onNodeWithContentDescription("FAB").performClick()
        composeTestRule.onNodeWithContentDescription("Enter Manually").performClick()
        composeTestRule.onNodeWithText("Internal Storage in Android").assertExists()
        composeTestRule.onNodeWithTag("wordField").performTextInput(str)
        composeTestRule.onNodeWithTag("wordField").assert(hasText(str))

        composeTestRule.onNodeWithText("Write Data to Internal Storage").performClick()
        composeTestRule.onNodeWithText("Read Data from Internal Storage").performClick()
        composeTestRule.onNodeWithText("Data will appear below : \n$str")
    }


    @Test
    fun testMemPersistence() {
        composeTestRule.setContent {
            FridgeTrackerAppTheme {
                FridgeTrackerApp()
            }
        }
        composeTestRule.onNodeWithContentDescription("FAB").performClick()
        composeTestRule.onNodeWithText("Enter Manually").performClick()
        composeTestRule.onNodeWithText("Internal Storage in Android").assertExists()
        composeTestRule.onNodeWithTag("wordField").performTextInput("BANANA")
        composeTestRule.onNodeWithTag("wordField").assert(hasText("BANANA"))

        composeTestRule.onNodeWithText("Write Data to Internal Storage").performClick()
        composeTestRule.onNodeWithText("Read Data from Internal Storage").performClick()
        composeTestRule.onNodeWithText("Data will appear below : \nBANANA").assertExists()



        composeTestRule.onNodeWithContentDescription("Scan Barcode").performClick()
        composeTestRule.onNodeWithContentDescription("FAB").performClick()
        composeTestRule.onNodeWithContentDescription("FAB").performClick()
        composeTestRule.onNodeWithContentDescription("Enter Manually").performClick()
        composeTestRule.onNodeWithText("Internal Storage in Android").assertExists()
        composeTestRule.onNodeWithText("Read Data from Internal Storage").performClick()
        composeTestRule.onNodeWithText("Data will appear below : \nBANANA").assertExists()
    }

    @Test
    fun writeToTextFieldAndSaveStressTest() {
        composeTestRule.setContent {
            FridgeTrackerAppTheme {
                FridgeTrackerApp()
            }
        }
        val str = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Aliquam ullamcorper nisi vitae nibh porta cursus. Cras porta vulputate urna vel venenatis. In ac quam ut leo vestibulum pharetra. Aenean non lectus a enim facilisis tincidunt ut ut ante. Fusce turpis lorem, tincidunt porttitor scelerisque in, vulputate vitae tellus. Ut lacus velit, semper et tempor sed, ultrices vitae neque. Pellentesque convallis neque et posuere ultricies. Fusce vitae neque dolor. Pellentesque condimentum, odio id posuere lobortis, purus est ultrices quam, vitae consectetur nunc nisl nec nibh. Sed in nulla placerat, ultrices leo quis, imperdiet ante.\n" +
                "\n" +
                "Integer luctus suscipit dui gravida lobortis. In ornare ac massa ut iaculis. Curabitur id lacus orci. Pellentesque ullamcorper dui sollicitudin mauris consectetur, quis viverra neque laoreet. Donec a eros urna. Fusce egestas libero nec laoreet pretium. Aliquam felis augue, suscipit eget vestibulum a, hendrerit sed nibh. Suspendisse feugiat mattis orci sodales ultricies. Morbi a purus convallis, condimentum magna eget, scelerisque ipsum. Sed ultricies, nisi a bibendum rutrum, mi enim interdum leo, et facilisis sem diam eu nisl.\n" +
                "\n" +
                "Quisque eu mi vitae mi tincidunt commodo ac ut lacus. Suspendisse tincidunt a leo lacinia aliquet. Nam pellentesque at eros a pellentesque. Donec placerat, arcu at tristique tempus, nibh augue volutpat enim, eget volutpat tortor tellus nec justo. Nulla elementum in lorem vitae finibus. Ut et mauris nec risus tincidunt faucibus. Aenean fringilla metus non urna commodo, eu semper odio viverra. Mauris vitae enim tincidunt, interdum libero mollis, eleifend risus. Quisque tincidunt orci sed tortor vestibulum, ut faucibus libero tincidunt. Sed ex neque, tristique et consequat non, malesuada nec mi. Nulla elementum laoreet augue id blandit. Pellentesque orci dolor, varius tincidunt risus in, efficitur aliquam sapien. Nunc venenatis lobortis ipsum id consectetur. Integer dictum mi sapien. Nulla a neque eu odio eleifend ullamcorper.\n" +
                "\n" +
                "Donec hendrerit imperdiet leo blandit viverra. Vivamus neque sem, placerat ac tortor non, eleifend accumsan ipsum. Praesent arcu erat, condimentum non pellentesque auctor, lobortis a tortor. In nec rutrum tellus. Suspendisse enim metus, semper non metus eu, euismod vestibulum libero. Nullam volutpat ultrices auctor. Etiam varius tortor vestibulum eros consectetur molestie sit amet vitae tortor. Nulla sit amet hendrerit urna. Etiam enim elit, elementum vitae orci in, tempus congue est. Vivamus commodo quam ante, in dignissim massa mattis vel. In hac habitasse platea dictumst. In sit amet maximus elit, non facilisis enim. Vestibulum feugiat lorem nec velit fringilla, ut lobortis nisi molestie. Etiam tincidunt felis nec tincidunt gravida. Suspendisse hendrerit non dui id posuere. Praesent placerat, dolor non venenatis viverra, urna metus lacinia nunc, eu pellentesque orci diam sed lectus.\n" +
                "\n" +
                "Phasellus mattis cursus tellus, at sagittis mi pellentesque a. Aenean vestibulum odio turpis, at iaculis lorem porttitor at. Nunc finibus lectus ac pretium pellentesque. Aliquam vestibulum magna ac quam feugiat, vitae laoreet sem placerat. Vivamus blandit, augue eu feugiat finibus, augue ipsum congue massa, id luctus lacus ex ut turpis. Suspendisse sit amet malesuada urna. Aliquam id fringilla neque. Mauris vel ultricies ligula. Phasellus non erat dictum, maximus urna facilisis, dignissim dolor. Etiam eget ornare lectus. Praesent luctus urna neque, malesuada sollicitudin lacus condimentum et. Integer suscipit ultricies fringilla. Nunc eu lobortis tortor. Vivamus justo lorem, porttitor sed mauris ut, porttitor bibendum nibh. Nam vestibulum eget libero non tincidunt. Nam bibendum tellus nec finibus bibendum.\n" +
                "\n" +
                "Ut venenatis mollis tristique. Nulla sit amet quam urna. Donec nunc odio, congue at mi eget, rhoncus ultrices ex. In interdum vulputate odio eget imperdiet. Nam sollicitudin tristique tincidunt. Nunc sed neque dui. Aliquam purus nisl, consectetur sed leo sit amet, fringilla efficitur sapien. Donec eleifend diam eu est auctor, quis aliquet neque consequat.\n" +
                "\n" +
                "Sed a pulvinar mi, eu efficitur magna. Suspendisse nec fringilla urna, ut pulvinar sem. Donec sed orci ut felis fringilla pretium eget eu libero. Aliquam id odio nisi. Phasellus eros dolor, dictum condimentum ipsum eu, molestie lacinia ligula. Aenean ut facilisis enim. Mauris eget consectetur erat. Morbi mattis, odio sed posuere accumsan, sapien elit ullamcorper sapien, vel feugiat massa elit nec nibh. Curabitur tortor mauris, blandit sit amet interdum vitae, ultricies in tortor. Ut vestibulum luctus nisi, ut tristique magna gravida non. Aenean et nunc nibh. Morbi tempor eget metus quis eleifend. In ut nunc pellentesque, iaculis urna vitae, hendrerit felis. Curabitur sagittis consequat fringilla. Sed vulputate convallis lobortis. Suspendisse rhoncus posuere lorem non eleifend.\n" +
                "\n" +
                "Nam et volutpat est. Sed pulvinar elit eleifend, consequat orci vel, vulputate ante. Donec suscipit odio sapien, quis ultrices elit vestibulum ut. Cras eget libero ut ex eleifend sollicitudin. Sed pellentesque purus sed feugiat vulputate. Integer vulputate imperdiet est et pulvinar. In orci nulla, mollis ac justo sed, consequat suscipit lorem. Aenean cursus, orci et vulputate mollis, erat velit consequat odio, et porta lacus eros ac tortor. Nullam est est, fringilla eget vulputate non, porttitor et est. Morbi et nisi sed dui tempor mattis quis eget libero.\n" +
                "\n" +
                "Aenean viverra arcu nisi, eget cursus dolor facilisis eu. Maecenas id enim placerat, convallis mi sed, pharetra dui. Orci varius natoque penatibus et magnis dis parturient montes, nascetur ridiculus mus. Phasellus blandit, massa vel ultrices volutpat, urna eros hendrerit nisl, sed consectetur enim mauris in nisl. Curabitur sed sollicitudin magna, ut faucibus odio. Etiam finibus dignissim felis et egestas. Fusce vitae est ultricies, condimentum sapien sit amet, malesuada libero. Aliquam aliquet, lectus at ultrices consectetur, orci augue tempus lacus, et molestie urna tortor venenatis augue. Morbi ultrices lobortis ipsum in dapibus.\n" +
                "\n" +
                "Proin odio magna, suscipit vel tempus nec, accumsan at quam. Vestibulum erat felis, fermentum quis tortor scelerisque, bibendum fermentum ligula. Ut vel tortor vestibulum, interdum massa a, suscipit ex. Vestibulum vitae ullamcorper nulla. Nunc ut porttitor nisl. Sed non sollicitudin orci. Nam eget purus commodo, interdum nisl et, congue metus. Cras feugiat tristique velit et ultricies. Quisque vestibulum mi metus, nec iaculis nulla vehicula ac. Donec scelerisque nisi lacus, nec iaculis tellus fermentum sit amet. Nam fermentum et dui tempor lobortis. Etiam sagittis nunc quis mi suscipit ultricies. Cras accumsan, quam ac faucibus consequat, libero elit aliquam odio, eu hendrerit nulla nunc a dui. Praesent ornare nec libero non pretium. Praesent malesuada purus a congue tristique. Proin et mollis tortor, in volutpat ante.\n" +
                "\n" +
                "Praesent condimentum est dui, quis dapibus sem rhoncus nec. Pellentesque euismod, ante porttitor facilisis congue, augue neque fermentum tortor, vitae semper ex augue nec nulla. Integer placerat sapien quis accumsan pellentesque. Maecenas ornare eros nec ante finibus congue. Quisque leo eros, lacinia at lorem id, hendrerit tristique velit. Ut eget ante venenatis tellus convallis sollicitudin id facilisis magna. Sed hendrerit eros in blandit suscipit. Vestibulum et dui sit amet ante finibus dignissim sed a enim. Donec commodo mi vel turpis ullamcorper tincidunt. Suspendisse non ligula posuere, interdum nulla sit amet, imperdiet sem. Phasellus maximus ipsum sed sapien posuere, ut vehicula urna sagittis. Nam pellentesque ligula eu convallis tincidunt. Mauris diam metus, mattis sit amet leo eget, egestas scelerisque augue. In hac habitasse platea dictumst. In vitae nisl sollicitudin, congue erat non, aliquam purus. Nunc viverra sed lacus ac ultrices.\n" +
                "\n" +
                "Proin blandit ullamcorper nibh, ac accumsan metus blandit quis. Curabitur turpis nibh, fermentum id pharetra at, ultricies vitae lectus. Mauris et dolor a turpis bibendum facilisis. Pellentesque vulputate placerat augue, pretium fermentum quam. Proin auctor tempus pellentesque. Vestibulum ante ipsum primis in faucibus orci luctus et ultrices posuere cubilia curae; Aenean pulvinar pellentesque nunc, non tristique ante pretium eu.\n" +
                "\n" +
                "Ut porta, nulla ac mollis vulputate, neque massa elementum urna, sed tincidunt enim dolor et ipsum. Aliquam malesuada ante sed mauris varius dignissim. Quisque bibendum turpis eu neque blandit, ut egestas arcu rutrum. Maecenas pulvinar, eros at imperdiet aliquam, nisi metus posuere ipsum, sed luctus arcu enim in velit. Aliquam maximus, magna sed tempor volutpat, enim tortor lacinia diam, et bibendum lectus odio at ex. Praesent facilisis ligula eget accumsan consequat. Aenean volutpat massa a mi elementum, non condimentum massa euismod. Aliquam erat volutpat. Cras aliquam tellus sit amet volutpat imperdiet.\n" +
                "\n" +
                "Etiam rutrum nisi mauris, quis malesuada dolor egestas sed. Pellentesque sem turpis, venenatis at venenatis et, hendrerit ut dui. Donec lobortis neque non eros blandit, vitae iaculis turpis hendrerit. Aliquam vel volutpat ante. In vel nisi vitae massa tempor rhoncus. Sed vel lorem nulla. Vestibulum nec tortor purus. Etiam venenatis ultrices quam et vehicula. Nunc aliquet purus justo, non tincidunt eros pulvinar id. Sed sit amet metus non quam fringilla pulvinar. Duis id libero orci. Nullam eget mi eu diam luctus scelerisque. Integer quis urna et urna accumsan efficitur. Maecenas luctus dolor sit amet lectus interdum, at euismod lorem fringilla.\n" +
                "\n" +
                "In accumsan nisi et tristique auctor. Vestibulum tempor turpis sed nunc convallis interdum. Vestibulum rutrum at urna sit amet imperdiet. Nulla cursus vehicula nibh, et placerat mauris gravida sed. Nunc auctor ornare nisi eget tincidunt. Nunc molestie turpis ac dui imperdiet placerat. Donec ut elit arcu. Quisque dignissim est ultricies libero gravida, ac luctus magna auctor. Morbi ante enim, accumsan sed semper sit amet, tempus sed felis. Nam tortor quam, lobortis id elit bibendum, cursus placerat orci. Maecenas faucibus dui sit amet nulla iaculis ornare. Nunc volutpat leo ut commodo convallis.\n" +
                "\n" +
                "Aliquam ornare, turpis quis viverra porta, augue sem vestibulum justo, ac ullamcorper ligula nisi maximus nisi. Nunc iaculis eros nisl, id semper metus efficitur at. Sed ipsum metus, dignissim et dolor vel, auctor mollis massa. Nullam et molestie felis. Integer neque diam, blandit non massa in, finibus porttitor nisi. Nullam tristique eros nisl, eu porttitor purus tempor eget. Interdum et malesuada fames ac ante ipsum primis in faucibus.\n" +
                "\n" +
                "Curabitur vel dui ac lectus porta ultricies ut eu odio. In imperdiet elit sit amet nisi volutpat ornare. Vivamus dictum elit a erat elementum semper. Proin malesuada consequat justo in vehicula. Pellentesque fringilla convallis tempus. Donec blandit velit vel velit fringilla cursus. Nulla pellentesque aliquet diam lacinia fringilla. Sed euismod est non sagittis vehicula. Nunc ac lacus sem.\n" +
                "\n" +
                "Vivamus est dolor, convallis vel elit sit amet, gravida elementum urna. Nullam id ornare ipsum. Praesent rhoncus ipsum ut mollis eleifend. Nulla ullamcorper odio id urna molestie, sed malesuada urna molestie. Duis sem urna, vestibulum posuere blandit at, cursus porttitor diam. Donec ac volutpat arcu, id vulputate magna. Nam maximus dui fermentum commodo ultricies. Nunc non feugiat dui. Duis cursus vel diam non commodo. Aliquam maximus, libero quis vestibulum dignissim, mauris magna interdum mauris, vitae dapibus lectus tellus ut risus.\n" +
                "\n" +
                "Praesent eu diam vitae eros ullamcorper pretium ut eu magna. Cras posuere est ut ultrices faucibus. Mauris tempus leo sed euismod ultrices. Quisque semper tincidunt mi. Maecenas fermentum consectetur luctus. Nulla lorem lacus, hendrerit non placerat non, eleifend quis massa. Donec vel purus vel nisi placerat tincidunt in finibus dui. Donec porttitor consectetur est quis venenatis. Donec id odio at magna finibus elementum nec non augue. Fusce vestibulum ante ac condimentum semper. Proin non diam venenatis, elementum libero ac, consequat libero. Sed in fermentum tortor. Integer facilisis quam eu lorem congue, sed aliquet lacus rhoncus. Vivamus convallis malesuada auctor.\n" +
                "\n" +
                "Cras hendrerit nisi ultricies, rhoncus ligula at, pretium neque. Nulla id viverra turpis. Quisque mattis ultricies est. Aenean ut nulla vel magna rutrum tempor ut tempor est. Praesent quis porta arcu. Quisque ac dictum arcu. Mauris sagittis mi quis quam fringilla, at iaculis neque vulputate. Donec venenatis dui at diam iaculis, ac interdum orci tincidunt. Vestibulum justo augue, venenatis sollicitudin iaculis et, suscipit at ex. Morbi laoreet aliquet dui, id pellentesque lectus vestibulum id. Orci varius natoque penatibus et magnis dis parturient montes, nascetur ridiculus mus. Donec augue augue, convallis vitae malesuada eu, molestie sit amet urna. Vivamus et sem massa."
        composeTestRule.onNodeWithContentDescription("FAB").performClick()
        composeTestRule.onNodeWithContentDescription("Enter Manually").performClick()
        composeTestRule.onNodeWithText("Internal Storage in Android").assertExists()
        composeTestRule.onNodeWithTag("wordField").performTextInput(str)
        composeTestRule.onNodeWithTag("wordField").assert(hasText(str))

        composeTestRule.onNodeWithText("Write Data to Internal Storage").performClick()
        composeTestRule.onNodeWithText("Read Data from Internal Storage").performClick()
        composeTestRule.onNodeWithText("Data will appear below : \n$str").assertExists()

    }


    @Test
    fun testMemPersistenceAndOverwrite() {
        composeTestRule.setContent {
            FridgeTrackerAppTheme {
                FridgeTrackerApp()
            }
        }

        composeTestRule.onNodeWithContentDescription("FAB").performClick()
        composeTestRule.onNodeWithContentDescription("Enter Manually").performClick()
        composeTestRule.onNodeWithText("Internal Storage in Android").assertExists()

        val str1 = "PINEAPPLE"
        val str2 = "BANANA"
        composeTestRule.onNodeWithTag("wordField").performTextInput(str1)
        composeTestRule.onNodeWithTag("wordField").assert(hasText(str1))


        composeTestRule.onNodeWithText("Write Data to Internal Storage").performClick()
        composeTestRule.onNodeWithText("Read Data from Internal Storage").performClick()
        composeTestRule.onNodeWithText("Data will appear below : \n$str1").assertExists()



        composeTestRule.onNodeWithContentDescription("Scan Barcode").performClick()
        composeTestRule.onNodeWithContentDescription("FAB").performClick()
        composeTestRule.onNodeWithContentDescription("FAB").performClick()
        composeTestRule.onNodeWithContentDescription("Enter Manually").performClick()
        composeTestRule.onNodeWithText("Internal Storage in Android").assertExists()
        composeTestRule.onNodeWithText("Read Data from Internal Storage").performClick()
        composeTestRule.onNodeWithText("Data will appear below : \n$str1").assertExists()

        composeTestRule.onNodeWithTag("wordField").performTextInput(str2)
        composeTestRule.onNodeWithTag("wordField").assert(hasText(str2))

        composeTestRule.onNodeWithText("Write Data to Internal Storage").performClick()
        composeTestRule.onNodeWithText("Read Data from Internal Storage").performClick()
        composeTestRule.onNodeWithText("Data will appear below : \n$str2").assertExists()


    }

    @Test
    fun testMemPersistenceAndOverwriteWithEmptyStr() {
        composeTestRule.setContent {
            FridgeTrackerAppTheme {
                FridgeTrackerApp()
            }
        }

        val str1 = "TEST STRING \ntest string"
        val str2 = ""

        composeTestRule.onNodeWithContentDescription("FAB").performClick()
        composeTestRule.onNodeWithText("Enter Manually").performClick()
        composeTestRule.onNodeWithText("Internal Storage in Android").assertExists()
        composeTestRule.onNodeWithTag("wordField").performTextInput(str1)
        composeTestRule.onNodeWithTag("wordField").assert(hasText(str1))

        composeTestRule.onNodeWithText("Write Data to Internal Storage").performClick()
        composeTestRule.onNodeWithText("Read Data from Internal Storage").performClick()
        composeTestRule.onNodeWithText("Data will appear below : \n$str1").assertExists()



        composeTestRule.onNodeWithContentDescription("Scan Barcode").performClick()
        composeTestRule.onNodeWithContentDescription("FAB").performClick()
        composeTestRule.onNodeWithContentDescription("FAB").performClick()
        composeTestRule.onNodeWithContentDescription("Enter Manually").performClick()
        composeTestRule.onNodeWithText("Internal Storage in Android").assertExists()
        composeTestRule.onNodeWithText("Read Data from Internal Storage").performClick()
        composeTestRule.onNodeWithText("Data will appear below : \n$str1").assertExists()

        composeTestRule.onNodeWithTag("wordField").performTextInput(str2)
        composeTestRule.onNodeWithTag("wordField").assert(hasText(str2))

        composeTestRule.onNodeWithText("Write Data to Internal Storage").performClick()
        composeTestRule.onNodeWithText("Read Data from Internal Storage").performClick()
        composeTestRule.onNodeWithText("Data will appear below : \n$str2").assertExists()
    }

    @Test
    fun writeToTextFieldAndSaveStrWithTerminator() {
        composeTestRule.setContent {
            FridgeTrackerAppTheme {
                FridgeTrackerApp()
            }
        }

        val str = "test\u0000test"

        composeTestRule.onNodeWithContentDescription("FAB").performClick()
        composeTestRule.onNodeWithContentDescription("Enter Manually").performClick()
        composeTestRule.onNodeWithText("Internal Storage in Android").assertExists()
        composeTestRule.onNodeWithTag("wordField").performTextInput(str)
        composeTestRule.onNodeWithTag("wordField").assert(hasText(str))

        composeTestRule.onNodeWithText("Write Data to Internal Storage").performClick()
        composeTestRule.onNodeWithText("Read Data from Internal Storage").performClick()
        composeTestRule.onNodeWithText("Data will appear below : \n$str")
    }

}