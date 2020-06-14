package alex.carcar.criminalintent

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.DialogFragment
import java.io.File


private const val ARG_IMAGE = "image"

class CrimePhotoFragment : DialogFragment() {
    private lateinit var photoView: ImageView
    private lateinit var photoFile: File

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(STYLE_NORMAL, R.style.Theme_AppCompat)
    }

    private fun updatePhotoView() {
        if (photoFile.exists()) {
            val bitmap = getScaledBitmap(photoFile.path, requireActivity())
            photoView.setImageBitmap(bitmap)
        } else {
            photoView.setImageDrawable(null)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_crime_photo, container, false)
        photoView = view.findViewById(R.id.crime_detail_photo) as ImageView
        photoFile = arguments?.getSerializable(ARG_IMAGE) as File
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        updatePhotoView()
    }

    companion object {
        fun newInstance(photoFile: File): CrimePhotoFragment {
            val args = Bundle().apply {
                putSerializable(ARG_IMAGE, photoFile)
            }
            return CrimePhotoFragment().apply {
                arguments = args
            }
        }
    }
}