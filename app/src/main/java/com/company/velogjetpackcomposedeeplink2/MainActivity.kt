package com.company.velogjetpackcomposedeeplink2

import android.app.PendingIntent
import android.app.TaskStackBuilder
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.company.velogjetpackcomposedeeplink2.ui.theme.VelogJetpackComposeDeepLink2Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            VelogJetpackComposeDeepLink2Theme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                        Button(onClick = {
                            val intent = Intent(
                                Intent.ACTION_VIEW,
                                Uri.parse("https://geonnuyasha.com/777")
                                // 해당 URL 주소를 볼 수 있는 웹 브라우저 또는 앱을 열도록 하는 intent 생성
                            )
                            val pendingIntent = TaskStackBuilder.create(applicationContext).run {
                                // TaskStackBuilder.create()를 통해서 앱들의 스택을 관리하는 TaskStackBuilder 객체 생성
                                // TaskStackBuilder 객체는 안드로이드의 백스택(액티비티들이 쌓이는 스택구조)을 생성한다.
                                addNextIntentWithParentStack(intent)
                                // 해당 intent와 intent의 부모 컨텍스트에 대한 정보를 백스택에 넣어준다.
                                // 이렇게 하면, A앱을 사용하던 중에 B앱의 알림이 울려서 사용자가 B앱을 들어갔을 때
                                // 뒤로가기 버튼을 누르면 다시 A앱으로 돌아오게 한다.
                                getPendingIntent(
                                    0,
                                    PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
                                )// 해당 intent를 실행 할 수 있는 PendingIntent 객체 생성
                            }
                            pendingIntent.send()// 위에서 만든 PendingIntent 실행
                        }
                        ) {
                            Text(text = "Trigger Deeplink")
                        }
                    }
                }
            }
        }
    }
}
