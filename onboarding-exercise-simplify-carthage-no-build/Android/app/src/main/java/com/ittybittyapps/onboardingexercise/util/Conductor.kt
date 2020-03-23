package com.ittybittyapps.onboardingexercise.util

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bluelinelabs.conductor.Controller
import com.bluelinelabs.conductor.RouterTransaction

fun Controller.asTransaction() = RouterTransaction.with(this)

/**
 * A [Controller] that manages the lifecycle of some kind of `ViewModel` and `ViewBinding`.
 *
 * A `ViewModel` is useful for housing state and logic related to View data. It survives orientation
 * changes.
 *
 * A `ViewBinding` has the same lifecycle as an Android [View]. This is useful for wiring up data
 * into Android views, as well as providing hooks into touch events so long as a `View` exists.
 */
abstract class ScopedController<VM : Any, VB : Any>(args: Bundle? = null) : Controller(args) {

    private var viewModel: VM? = null
    private var viewBinder: VB? = null

    final override fun onCreateView(inflater: LayoutInflater, container: ViewGroup): View {
        val view = onCreateView(container)
        val context = view.context.applicationContext
        val currentViewModel: VM = viewModel ?: onCreateViewModel(context).also { viewModel = it }
        viewBinder = onCreateViewBinder(view, currentViewModel)
        return view
    }

    final override fun onAttach(view: View) {
        val currentVm = requireNotNull(viewModel) { "viewModel shouldn't be null in onAttach()." }
        val currentBinder =
            requireNotNull(viewBinder) { "viewBinder shouldn't be null in onAttach()." }
        onAttach(view, currentBinder, currentVm)
    }

    final override fun onDetach(view: View) {
        val currentVm = requireNotNull(viewModel) { "viewModel shouldn't be null in onDetach()." }
        val currentBinder =
            requireNotNull(viewBinder) { "viewBinder shouldn't be null in onDetach()." }
        onDetach(view, currentBinder, currentVm)
    }

    final override fun onDestroyView(view: View) {
        val currentBinder =
            requireNotNull(viewBinder) { "viewBinder shouldn't be null in onDestroyView()." }
        onDestroyView(view, currentBinder)
        viewBinder = null
    }

    final override fun onDestroy() {
        onDestroy(viewModel)
        viewModel = null
    }

    abstract fun onCreateView(container: ViewGroup): View
    abstract fun onCreateViewModel(context: Context): VM
    abstract fun onCreateViewBinder(view: View, viewModel: VM): VB
    open fun onAttach(view: View, binder: VB, viewModel: VM) {}
    open fun onDetach(view: View, binder: VB, viewModel: VM) {}
    open fun onDestroyView(view: View, binder: VB) {}
    open fun onDestroy(viewModel: VM?) {}
}
