package com.neotech.dialcodes;

import com.neotech.dialcodes.data.DataContainer;
import com.neotech.dialcodes.data.service.WikiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

/**
 * Created by Artis on 2/20/2017.
 */

@Component
public class DataLoadRunner implements ApplicationRunner {

    @Value("${wiki.url}")
    private String wikiUrl;

    private final WikiService wikiService;
    private final DataContainer dataContainer;

    @Autowired
    public DataLoadRunner(WikiService wikiService, DataContainer dataContainer) {
        this.wikiService = wikiService;
        this.dataContainer = dataContainer;
    }

    @Override
    public void run(ApplicationArguments applicationArguments) throws Exception {
        dataContainer.setCountries(wikiService.loadCountries(wikiUrl));
    }
}
