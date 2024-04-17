package ru.btpit.zadanie2.activity

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController

import ru.btpit.zadanie2.databinding.FragmentNewPostBinding
import ru.btpit.zadanie2.util.StringArg

import ru.netology.nmedia.util.AndroidUtils
import ru.netology.nmedia.viewmodel.PostViewModel

class NewPostFragment : Fragment() {

    companion object {
        var Bundle.textArg: String? by StringArg
    }

    private val viewModel: PostViewModel by viewModels(
        ownerProducer = ::requireParentFragment
    )

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentNewPostBinding.inflate(
            inflater,
            container,
            false
        )
        if (arguments?.textArg == null )
        {
            binding.Name.text ="Добавить пост"
        }
     else
        {
            binding.Name.text ="Изменение поста"
        }

        arguments?.textArg
            ?.let(binding.edit::setText)

        binding.ok.setOnClickListener {
            viewModel.changeContent(binding.edit.text.toString())
            viewModel.save()
            AndroidUtils.hideKeyboard(requireView())
            findNavController().navigateUp()
        }
        return binding.root
    }
}
////    override fun onCreate(savedInstanceState: Bundle?) {
////        super.onCreate(savedInstanceState)
////        val binding = ActivityNewPostBinding.inflate(layoutInflater)
////        setContentView(binding.root)
//
//        val text = intent.getStringExtra(Intent.EXTRA_TEXT)
//        binding.edit.text = Editable.Factory.getInstance().newEditable(text)
//        if (text == "")
//        {
//            binding.Name.text = "Создание поста"
//        }
//        else binding.Name.text = "Редактирование поста"
//        binding.edit.requestFocus()
//        binding.ok.setOnClickListener {
//            val intent = Intent()
//            if (binding.edit.text.isNullOrBlank()) {
//                setResult(Activity.RESULT_CANCELED, intent)
//            } else {
//                val content = binding.edit.text.toString()
//                intent.putExtra(Intent.EXTRA_TEXT, content)
//                setResult(Activity.RESULT_OK, intent)
//            }
//            finish()
//        }
//
//    }
//}