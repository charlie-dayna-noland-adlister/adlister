package model;

import java.util.ArrayList;
import java.util.List;

public class ListAdsDao implements Ads {
    private List<AdImp> adImps;

    public List<AdImp> all() {
        if (adImps == null) {
            adImps = generateAds();
        }
        return adImps;
    }

    private List<AdImp> generateAds() {
        List<AdImp> adImps = new ArrayList<>();
        adImps.add(new AdImp(
            1,
            1,
            "playstation for sale",
            "This is a slightly used playstation"
        ));
        adImps.add(new AdImp(
            2,
            1,
            "Super Nintendo",
            "Get your game on with this old-school classic!"
        ));
        adImps.add(new AdImp(
            3,
            2,
            "Junior Java Developer Position",
            "Minimum 7 years of experience required. You will be working in the scripting language for Java, JavaScript"
        ));
        adImps.add(new AdImp(
            4,
            2,
            "JavaScript Developer needed",
            "Must have strong Java skills"
        ));
        return adImps;
    }
}
