package com.test.movie.ui.fragment.images

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.provider.MediaStore
import android.view.View
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.viewModels
import com.google.android.material.snackbar.Snackbar
import com.test.movie.R
import com.test.movie.core.location.ImageName
import com.test.movie.databinding.FragmentUploadImageBinding
import com.test.movie.ui.common.BaseFragment
import com.test.movie.ui.utils.toImageList

class UploadImageFragment : BaseFragment<FragmentUploadImageBinding>() {

    val viewModel: UploadImageViewModel by viewModels()

    private lateinit var resultLaunch: ActivityResultLauncher<Intent>
    override fun onAttach(context: Context) {
        super.onAttach(context)
        resultLaunch = registerForActivityResult(
            ActivityResultContracts.StartActivityForResult()
        ) { result ->
            if (result.resultCode == Activity.RESULT_OK) {
                // There are no request codes
                val data = result.data?.data
                data?.let { uri ->
                    bind.loading.visibility = View.VISIBLE
                    viewModel.saveImage(uri, requireActivity()){
                        bind.loading.visibility = View.GONE
                    }
                }
            }
        }
    }
    override fun initElements() {
        viewModel.apply{
            getImages{ list ->
                if(list.isNotEmpty()){
                    bind.rvImages.adapter = ImageAdapter(list.toImageList())
                }else {
                    Snackbar.make(requireView(), R.string.no_results, Snackbar.LENGTH_LONG).setAction("Cerrar"){

                    }.show()
                }
            }
        }
    }

    override fun getLayout(): Int = R.layout.fragment_upload_image

    override fun initObservers() {

    }

    override fun initView() {
        bind.apply {
            uploadImage.setOnClickListener {
                val i = Intent(
                    Intent.ACTION_PICK,
                    MediaStore.Images.Media.EXTERNAL_CONTENT_URI
                )
                resultLaunch.launch(i)
            }
        }
    }
}