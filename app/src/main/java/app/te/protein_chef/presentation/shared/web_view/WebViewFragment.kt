package app.te.protein_chef.presentation.shared.web_view

import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.Bitmap
import android.view.View
import androidx.fragment.app.viewModels
import app.te.protein_chef.R
import app.te.protein_chef.databinding.FragmentWebViewBinding
import app.te.protein_chef.presentation.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint
import im.delight.android.webview.AdvancedWebView

@AndroidEntryPoint
class WebViewFragment : BaseFragment<FragmentWebViewBinding>(), AdvancedWebView.Listener {

  private val viewModel: WebViewViewModel by viewModels()

  override
  fun getLayoutId() = R.layout.fragment_web_view

  override
  fun setBindingVariables() {
    binding.viewModel = viewModel
  }

  override
  fun getFragmentArguments() {

  }

  override
  fun setUpViews() {
    binding.webview.setListener(requireActivity(), this)
    binding.webview.setMixedContentAllowed(false)
    binding.webview.setDesktopMode(true)
    binding.webview.loadUrl("https://tawk.to/chat/6293d7adb0d10b6f3e74a316/1g48o507k")

  }

  override fun onPageStarted(url: String?, favicon: Bitmap?) {
    binding.webview.visibility = View.VISIBLE

  }

  override fun onPageFinished(url: String?) {
    binding.webProgress.visibility = View.GONE
  }

  override fun onPageError(errorCode: Int, description: String?, failingUrl: String?) {}

  override fun onDownloadRequested(
    url: String?,
    suggestedFilename: String?,
    mimeType: String?,
    contentLength: Long,
    contentDisposition: String?,
    userAgent: String?
  ) {
  }

  override fun onExternalPageRequest(url: String?) {}

  override fun onActivityResult(requestCode: Int, resultCode: Int, intent: Intent?) {
    super.onActivityResult(requestCode, resultCode, intent)
    binding.webview.onActivityResult(requestCode, resultCode, intent)
  }

  @SuppressLint("NewApi")
  override fun onResume() {
    super.onResume()
    binding.webview.onResume()
  }

  @SuppressLint("NewApi")
  override fun onPause() {
    binding.webview.onPause()
    super.onPause()
  }

  override fun onDestroy() {
    binding.webview.onDestroy()
    super.onDestroy()
  }
}