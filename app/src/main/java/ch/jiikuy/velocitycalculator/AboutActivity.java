package ch.jiikuy.velocitycalculator;

import android.content.Context;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.net.Uri;

import com.danielstone.materialaboutlibrary.MaterialAboutActivity;
import com.danielstone.materialaboutlibrary.items.MaterialAboutActionItem;
import com.danielstone.materialaboutlibrary.items.MaterialAboutItemOnClickAction;
import com.danielstone.materialaboutlibrary.items.MaterialAboutTitleItem;
import com.danielstone.materialaboutlibrary.model.MaterialAboutCard;
import com.danielstone.materialaboutlibrary.model.MaterialAboutList;
import com.danielstone.materialaboutlibrary.ConvenienceBuilder;
import com.mikepenz.aboutlibraries.LibsBuilder;
import com.mikepenz.community_material_typeface_library.CommunityMaterial;
import com.mikepenz.google_material_typeface_library.GoogleMaterial;
import com.mikepenz.iconics.IconicsDrawable;

public class AboutActivity extends MaterialAboutActivity {

    @Override
    protected MaterialAboutList getMaterialAboutList(Context context) {
        // Build the cards
        MaterialAboutCard.Builder appCardBuilder = new MaterialAboutCard.Builder();
        buildApp(this, appCardBuilder);

        MaterialAboutCard.Builder authorCardBuilder = new MaterialAboutCard.Builder();
        buildAuthor(this, authorCardBuilder);

        MaterialAboutCard.Builder contributeCardBuilder = new MaterialAboutCard.Builder();
        buildContribute(this, contributeCardBuilder);

        return new MaterialAboutList.Builder()
                .addCard(appCardBuilder.build())
                .addCard(authorCardBuilder.build())
                .addCard(contributeCardBuilder.build())
                .build();
    }

    @Override
    protected CharSequence getActivityTitle() {
        // Set activity title
        return getString(R.string.about);
    }



    private void buildApp(final Context context, MaterialAboutCard.Builder appCardBuilder) {
        appCardBuilder.addItem(new MaterialAboutTitleItem.Builder()
                .text(getString(R.string.app_name))
                .icon(R.mipmap.ic_launcher)
                .build());
                try {
                    appCardBuilder.addItem(ConvenienceBuilder.createVersionActionItem(context, new IconicsDrawable(context)
                            .icon(GoogleMaterial.Icon.gmd_info)
                            .color(Color.GRAY)
                            .sizeDp(18), getString(R.string.about_version), false));
                } catch (PackageManager.NameNotFoundException e) {
                    e.printStackTrace();
                }

        appCardBuilder.addItem(new MaterialAboutActionItem.Builder()
                        .text(getString(R.string.about_sourcecode))
                        .icon(new IconicsDrawable(context)
                                .icon(CommunityMaterial.Icon.cmd_github_circle)
                                .color(Color.GRAY)
                                .sizeDp(18))
                        .setOnClickAction(ConvenienceBuilder.createWebsiteOnClickAction(context, Uri.parse("https://github.com/Jiikuy/VelocityCalculator")))
                        .build())
                .addItem(new MaterialAboutActionItem.Builder()
                        .text(getString(R.string.about_license))
                        .subText(getString(R.string.about_gpl))
                        .icon(new IconicsDrawable(context)
                                .icon(GoogleMaterial.Icon.gmd_book)
                                .color(Color.GRAY)
                                .sizeDp(18))
                        .setOnClickAction(ConvenienceBuilder.createWebsiteOnClickAction(context, Uri.parse("https://www.gnu.org/licenses/gpl.html")))
                        .build())
                .addItem(ConvenienceBuilder.createWebsiteActionItem(context, new IconicsDrawable(context)
                        .icon(CommunityMaterial.Icon.cmd_book_open)
                        .color(Color.GRAY)
                        .sizeDp(18), getString(R.string.about_documentation), false, Uri.parse("https://github.com/Jiikuy/VelocityCalculator/wiki/")))
                // TODO: add translators screen
                /*.addItem(new MaterialAboutActionItem.Builder()
                        .text(getString(R.string.about_translators))
                        .icon(new IconicsDrawable(context)
                                .icon(CommunityMaterial.Icon.cmd_translate)
                                .color(Color.GRAY)
                                .sizeDp(18))
                        .setOnClickAction(new MaterialAboutItemOnClickAction() {
                            @Override
                            public void onClick() {


                            }
                            })
                        .build())*/
                .addItem(new MaterialAboutActionItem.Builder()
                    .text(getString(R.string.about_opensourcelibs))
                    .icon(new IconicsDrawable(context)
                        .icon(CommunityMaterial.Icon.cmd_git)
                        .color(Color.GRAY)
                        .sizeDp(18))
                    .setOnClickAction(new MaterialAboutItemOnClickAction() {
                            @Override
                            public void onClick() {
                                LibsBuilder builder = new LibsBuilder();
                                        builder.withActivityTheme(R.style.AboutLibrariesTheme)
                                        .withAboutVersionShown(false)
                                        .withAutoDetect(true)
                                        .withAboutIconShown(false)
                                        .start(context);
                            }

                    })
                    .build());
    }

    private void buildAuthor(Context context, MaterialAboutCard.Builder authorCardBuilder) {
        authorCardBuilder.title(getString(R.string.about_author))
                .addItem(new MaterialAboutActionItem.Builder()
                        .text(getString(R.string.about_authorname))
                        .subText(getString(R.string.about_country))
                        .icon(new IconicsDrawable(context)
                                .icon(GoogleMaterial.Icon.gmd_person)
                                .color(Color.GRAY)
                                .sizeDp(18))
                        .build())
                .addItem(ConvenienceBuilder.createEmailItem(context, new IconicsDrawable(context)
                        .icon(GoogleMaterial.Icon.gmd_email)
                        .color(Color.GRAY)
                        .sizeDp(18), getString(R.string.about_sendemail), true, getString(R.string.about_emailaddress), ""))
                .addItem(new MaterialAboutActionItem.Builder()
                        .text(getString(R.string.about_github))
                        .subText(getString(R.string.about_githubusername))
                        .icon(new IconicsDrawable(context)
                                .icon(CommunityMaterial.Icon.cmd_github_circle)
                                .color(Color.GRAY)
                                .sizeDp(18))
                        .setOnClickAction(ConvenienceBuilder.createWebsiteOnClickAction(context, Uri.parse("https://github.com/Jiikuy/") ))
                        .build());
    }

    private void buildContribute(Context context, MaterialAboutCard.Builder contributeCardBuilder) {
        contributeCardBuilder.title(getString(R.string.about_contribute))
                .addItem(new MaterialAboutActionItem.Builder()
                        .text(getString(R.string.about_reportissue))
                        .subText(getString(R.string.about_reportissuehere))
                        .icon(new IconicsDrawable(context)
                                .icon(GoogleMaterial.Icon.gmd_bug_report)
                                .color(Color.GRAY)
                                .sizeDp(18))
                        .setOnClickAction(ConvenienceBuilder.createWebsiteOnClickAction(context, Uri.parse("https://github.com/Jiikuy/VelocityCalculator/issues")))
                        .build())
                .addItem(new MaterialAboutActionItem.Builder()
                        .text(getString(R.string.about_translate))
                        .subText(getString(R.string.about_translatehere))
                        .icon(new IconicsDrawable(context)
                                .icon(GoogleMaterial.Icon.gmd_translate)
                                .color(Color.GRAY)
                                .sizeDp(18))
                        .setOnClickAction(ConvenienceBuilder.createWebsiteOnClickAction(context, Uri.parse("https://www.poeditor.com/join/project/EIvRjgfGRO")))
                        .build());
    }

}
