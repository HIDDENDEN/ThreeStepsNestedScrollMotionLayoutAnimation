package com.example.threestepsnestedscrollmotionlayoutanimation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MotionEvent
import androidx.constraintlayout.motion.widget.MotionLayout
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.threestepsnestedscrollmotionlayoutanimation.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private val binding: ActivityMainBinding by viewBinding()

    var interceptScrollTouchFlag = true
        private set

    fun passTouchEventToMotionLayout(motionEvent: MotionEvent){
        binding.mainActivityMotion.onTouchEvent(motionEvent)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        createBottomSheet()
        listenTransitionChange()
    }

    private fun createBottomSheet(){
        val fr = ContentFragment.newInstance()
        val currentFragmentRoot = supportFragmentManager.fragments.lastOrNull()?.requireView()
        supportFragmentManager
            .beginTransaction()
            .apply {
                if( currentFragmentRoot!=null){
                    addSharedElement(currentFragmentRoot, currentFragmentRoot.transitionName)
                    setReorderingAllowed(true)
                    fr.sharedElementEnterTransition = BottomSheetSharedTransition()
                }
            }
            .replace(R.id.container, fr, null)
            .addToBackStack(fr.javaClass.name)
            .commit()
    }

    private fun listenTransitionChange(){
        binding.mainActivityMotion.setTransitionListener(object : MotionLayout.TransitionListener{
            override fun onTransitionStarted(motionLayout: MotionLayout?, startId: Int, endId: Int) {}
            override fun onTransitionChange(motionLayout: MotionLayout?, startId: Int, endId: Int, progress: Float) {}
            override fun onTransitionTrigger(motionLayout: MotionLayout?, triggerId: Int, positive: Boolean, progress: Float) {}

            override fun onTransitionCompleted(motionLayout: MotionLayout?, currentId: Int) {
                when(motionLayout?.currentState){
                   R.id.ThirdState -> {
                       interceptScrollTouchFlag = false
                   }
                    else -> {
                        interceptScrollTouchFlag = true
                    }
                }
            }
        })
    }
}