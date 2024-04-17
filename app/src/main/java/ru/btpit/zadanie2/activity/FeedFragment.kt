package ru.btpit.zadanie2.activity

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.viewModels
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import ru.btpit.zadanie2.R
import ru.btpit.zadanie2.activity.NewPostFragment.Companion.textArg
import ru.btpit.zadanie2.databinding.FragmentFeedBinding

import ru.netology.nmedia.dto.Post
import ru.netology.nmedia.viewmodel.PostViewModel



class FeedFragment : Fragment() {

    private val viewModel: PostViewModel by viewModels(
        ownerProducer = ::requireParentFragment
    )

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentFeedBinding.inflate(
            inflater,
            container,
            false
        )

//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        val binding = ActivityPostsBinding.inflate(layoutInflater)
//        setContentView(binding.root)
//        run {
//            val preferences = getPreferences(Context.MODE_PRIVATE)
//            preferences.edit().apply {
//                putString("key", "value")
//                commit()
//            }
//        }
//        run {
//            getPreferences(Context.MODE_PRIVATE)
//                .getString("key", "no value")?.let{
//                    Snackbar.make(binding.root, it, BaseTransientBottomBar.LENGTH_INDEFINITE)
//                        .show()
//                }
//        }

//        val newPostActivity = registerForActivityResult(NewPostResultContract()) { result ->
//            result ?: return@registerForActivityResult
//            viewModel.changeContent(result)
//            viewModel.save()

        val adapter = Posts(object : OnInteractionListener {
            override fun onEdit(post: Post) {
                viewModel.edit(post)
//                val text = post.content
//                newPostActivity.launch(text)
                val bundle = Bundle()
                bundle.textArg = post.content
                findNavController().navigate(R.id.action_feedFragment_to_newPostFragment, bundle)
            }

            override fun onLike(post: Post) {
                viewModel.like(post.id)
            }
            override fun onVideo(post: Post)
            {
                val url = "https://www.youtube.com/watch?v=qeQnMgega0k"
                val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
                startActivity(intent)
            }

            override fun onShare(post: Post) {

                    val intent = Intent().apply {
                        action = Intent.ACTION_SEND
                        putExtra(Intent.EXTRA_TEXT, post.content)
                        type = "text/plain"
                    }

                    val shareIntent = Intent.createChooser(intent, getString(R.string.chooser_share_post))
                    startActivity(shareIntent)

                    viewModel.share(post.id)
            }

            override fun onAuthorClicked(post: Post) {
                val bundle = Bundle()
                bundle.putLong("postId", post.id)
                findNavController().navigate(R.id.action_feedFragment_to_onePostFragment, bundle)

            }


            override fun onRemove(post: Post) {
                viewModel.removeById(post.id)
            }
        })
    binding.list.adapter = adapter
    viewModel.data.observe(viewLifecycleOwner) { posts ->
        adapter.submitList(posts)
    }
        binding.add.setOnClickListener {
            findNavController().navigate(R.id.action_feedFragment_to_newPostFragment)
        }
        return binding.root
//        val newPostLauncher = registerForActivityResult(NewPostResultContract()) { result ->
//            result ?: return@registerForActivityResult
//            viewModel.changeContent(result)
//            viewModel.save()
//        }

//        binding.add.setOnClickListener {
//            val text = ""
//            newPostLauncher.launch(text)
//
//        }

//        viewModel.edited.observe(this) { post ->
//            if (post.id == 0L) {
//                return@observe
//            }

//            with(binding.contentPost) {
//                requestFocus()
//                setText(post.content)
//            }
//        }

        // Обработчик нажатия на кнопку share


//        val adapterr = posts {
//            viewModel.share(it.id)
//        }
//        binding.list.adapter = adapterr
//        viewModel.data.observe(this) { posts ->
//            adapter.submitList(posts)
//        }

//        val viewModel: PostViewModel by viewModels()
//        viewModel.data.observe(this) { post ->
//            post.map {post ->
//                binding.container.removeAllViews()
//                ActivityMainBinding.inflate(layoutInflater, binding., true).apply{
//                    author.text = post.author
//                    published.text = post.published
//                    content.text = post.content
//                    val formattedLike = formatNumber(post.likecount)
//                    textView.text = formattedLike
//                    val formattedShare = formatNumber1(post.share)
//                    textView2.text = formattedShare
//
//
//                    like.setImageResource(
//                        if (post.isLiked) R.mipmap.like2 else R.mipmap.like
//
//                    )
//                    like.setOnClickListener {
//
//                        viewModel.like(post.id)
//
//
//                    }
//                    share.setOnClickListener {
//
//
//                        viewModel.share(post.id)
//                    }
//                }.root
//
//            }
//        }

//        binding.save.setOnClickListener {
//            with(binding.contentPost) {
//                if (text.isNullOrBlank()) {
//                    Toast.makeText(
//                        this@MainActivity,
//                        context.getString(R.string.errorAdd),
//                        Toast.LENGTH_SHORT
//                    ).show()
//                    return@setOnClickListener
//                }
//
//                viewModel.changeContent(text.toString())
//                viewModel.save()
//
//                setText("")
//                clearFocus()
//                AndroidUtils.hideKeyboard(this)
//            }
//        }



    }

}



