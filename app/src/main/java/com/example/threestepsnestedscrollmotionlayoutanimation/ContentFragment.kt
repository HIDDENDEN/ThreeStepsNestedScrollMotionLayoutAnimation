package com.example.threestepsnestedscrollmotionlayoutanimation

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.threestepsnestedscrollmotionlayoutanimation.databinding.FragmentContentBinding

class ContentFragment: Fragment() {

    private val binding: FragmentContentBinding by viewBinding()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_content,container,false)
    }

    @SuppressLint("ClickableViewAccessibility")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.nestedScrollContainer.setOnTouchListener { v , motionEvent ->

            if (rootActivity().interceptScrollTouchFlag){
                rootActivity().passTouchEventToMotionLayout(motionEvent)
            }
            return@setOnTouchListener false
        }
    }

    fun rootActivity(): MainActivity = requireActivity() as MainActivity

    companion object{
        @JvmStatic
        fun newInstance(): ContentFragment = ContentFragment()
    }

}