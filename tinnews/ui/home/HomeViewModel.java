package com.laioffer.tinnews.ui.home;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;
import androidx.lifecycle.ViewModel;

import com.laioffer.tinnews.model.Article;
import com.laioffer.tinnews.model.NewsResponse;
import com.laioffer.tinnews.repository.NewsRepository;

//data store
//observable UI state: LiveData<NewsResponse>
public class HomeViewModel extends ViewModel {
    private final NewsRepository repository;
    private final MutableLiveData<String> countryInput = new MutableLiveData<>();

    public HomeViewModel(NewsRepository newsRepository) {
        this.repository = newsRepository;
    }

    //event
    public void setCountryInput(String country) {
        // put data store change logic into event
        countryInput.setValue(country);
    }

    //event
    public void setFavoriteArticleInput(Article article) {
        repository.favoriteArticle(article);
    }

    //country input livedata -> switch -> top headline livedata
    public LiveData<NewsResponse> getTopHeadlines() {
        return Transformations.switchMap(countryInput, repository::getTopHeadlines);
        //Transformations.switchMap(x, f(x)), return y = f(x)
        //input(x): countryInput
        //func(f(x)): repository::getTopHeadlines
        //output(y) = LiveData<NewsResponse>
    }
}
