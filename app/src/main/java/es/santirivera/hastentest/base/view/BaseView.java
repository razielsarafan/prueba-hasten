package es.santirivera.hastentest.base.view;

import android.view.View;

import butterknife.ButterKnife;
import es.santirivera.hastentest.base.activity.BaseActivity;


public abstract class BaseView<ViewInterfaceType> {

	protected final BaseActivity baseActivity;
	protected final ViewInterfaceType presenter;

	protected View mainView;

	public BaseView(BaseActivity baseActivity, ViewInterfaceType presenter) {
		this.baseActivity = baseActivity;
		this.presenter = presenter;
	}

	public abstract int getContentView();

	public void setupLayout(View mainView) {
		this.mainView = mainView;
		ButterKnife.bind(this, mainView);
		prepareView();
	}

	protected abstract void prepareView();
}
