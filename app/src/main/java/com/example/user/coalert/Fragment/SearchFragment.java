package com.example.user.coalert.Fragment;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.Intent;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.FileProvider;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethod;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ViewFlipper;

import com.example.user.coalert.Activity.CosmeticInformationActivity;
import com.example.user.coalert.Adapter.NotFocusSearchAdapter;
import com.example.user.coalert.Adapter.searchAdapter;
import com.example.user.coalert.Background;
import com.example.user.coalert.R;
import com.example.user.coalert.Singleton.ForBackgroundSingleton;
import com.example.user.coalert.Singleton.ForRestSingleton;
import com.example.user.coalert.forRestServer.GetBadIngredientModel;
import com.example.user.coalert.forRestServer.searchModel;
import com.example.user.coalert.item.OneImgOneStringCardView;
import com.googlecode.tesseract.android.TessBaseAPI;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;

import static com.facebook.FacebookSdk.getApplicationContext;


public class SearchFragment extends Fragment {
    FragmentTransaction fragmentTransaction;
    EditText edit;
    String text;
    Boolean checkCameraSet = true;
    LinearLayout linearLayout;
    InputMethodManager imm;
    Uri allUri;
    int previousText = 0;
    int cameraRequest = 10;
    List<String> list;
    String datapath;
    TessBaseAPI mTess;
    Bitmap image;
    ListView searchList;
    ArrayList<OneImgOneStringCardView> arrayList;
    NotFocusSearchAdapter notFocusSearchAdapter;
    RecyclerView cosmeticCompanySpecial;

    public SearchFragment() {
        // Required empty public constructor
    }

    @SuppressLint("CommitTransaction")
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @SuppressLint({"ClickableViewAccessibility", "ServiceCast"})
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View v = inflater.inflate(R.layout.fragment_search, container, false);
        final ViewFlipper viewFlipper = v.findViewById(R.id.viewFlipper);
        arrayList = new ArrayList<>();
        cosmeticCompanySpecial = v.findViewById(R.id.notFocusSearchViewRecyclerView);
        cosmeticCompanySpecial.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayout.VERTICAL, false));
        arrayList = addData(arrayList);
        notFocusSearchAdapter = new NotFocusSearchAdapter(arrayList, getContext());
        cosmeticCompanySpecial.setHasFixedSize(true);
        cosmeticCompanySpecial.setFocusable(false);
        cosmeticCompanySpecial.setAdapter(notFocusSearchAdapter);


        searchList = v.findViewById(R.id.search_list);
        searchList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, final View view, final int position, long l) {
                Log.e("asdasd", list.get(position));

                new Thread(){
                    @Override
                    public void run() {
                        super.run();
                        try {
                            Intent intent = new Intent(view.getContext(), CosmeticInformationActivity.class);
                            intent.putExtra("cname", list.get(position));
                            intent.putExtra("check", 0);
                            intent.putExtra("image", R.drawable.medi_uv_ultra);
                            intent.putExtra("company", "닥터지");
                            intent.putExtra("kind", 1);
                            Call<List<GetBadIngredientModel>> call = ForRestSingleton.getInstance().ingredientPerCosmetic(list.get(position), "1");
                            List<GetBadIngredientModel> result = call.execute().body();
                            intent.putExtra("ingredient", result.toString());
                            Log.e("asdasd", result.toString());
                            startActivity(intent);
                        }catch (Exception e){
                            e.printStackTrace();
                        }
                    }
                }.start();

            }
        });

        final ImageButton imageButton = v.findViewById(R.id.search_camera);
        imageButton.setOnClickListener(new View.OnClickListener() {
                                           @Override
                                           public void onClick(View view) {
                                               cameraView();
                                           }
                                       }
        );

        edit = v.findViewById(R.id.editSearch);
        //edit Text 포커스가 있냐 없냐에 따라서
        //이벤트 구분
        //ViewFlipper를 통해서 구현
        edit.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean haveFocus) {
                final ListView listView = v.findViewById(R.id.search_list);
                if (haveFocus) {
                    viewFlipper.setDisplayedChild(1);
                    //List는 인터페이스이고
                    //list는 순간 순간 변하는 데이터들을 저장하고 뺴고 하기위한 역활
                    list = new ArrayList<>();
                    settingList(list);
                    final ArrayList<String> arrayList = new ArrayList<String>(list);
                    final searchAdapter searchAdapter = new searchAdapter(list, getActivity());
                    listView.setAdapter(searchAdapter);
                    edit.addTextChangedListener(new TextWatcher() {
                        @Override
                        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                        }

                        @Override
                        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                        }

                        @Override
                        public void afterTextChanged(Editable editable) {
                            String text = edit.getText().toString();
                            search(text, list, arrayList, searchAdapter);
                        }
                    });
                }else{
                    viewFlipper.setDisplayedChild(0);
                }
            }
        });

        edit.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int actionId, KeyEvent keyEvent) {
                switch (actionId) {
                    case EditorInfo.IME_ACTION_SEARCH:
                        //TODO 검색 버튼을 눌러줬을 때 이벤트입니다.
                        break;
                }
                return true;
            }
        });


// 화면 누르면  focus제거
//        imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
//        viewFlipper.setOnTouchListener(new View.OnTouchListener() {
//            @Override
//            public boolean onTouch(View view, MotionEvent motionEvent) {
//                Log.e("touch: ","my body");
//                edit.clearFocus();
//                imm.hideSoftInputFromWindow(edit.getWindowToken(), 0);
//                return true;
//            }
//
//        });

        return v;
    }

    public void cameraView() {
        Uri uri = FileProvider.getUriForFile(getActivity(), "com.bignerdranch.android.test.fileprovider", new File(Environment.getExternalStorageDirectory(), "tmp_contact_" + System.currentTimeMillis() + ".jpg"));
        allUri = uri;
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        intent.putExtra(android.provider.MediaStore.EXTRA_OUTPUT, uri);
        startActivityForResult(intent, cameraRequest);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == cameraRequest && resultCode == Activity.RESULT_OK) {
            try {
                image = MediaStore.Images.Media.getBitmap(getActivity().getContentResolver(), allUri);
                image = Bitmap.createScaledBitmap(image, image.getWidth() / 3, image.getHeight() / 3, true);
                image = rotateBitmap(image);
                Log.e("int ActivityResult:", "asdasd");
            } catch (IOException e) {
                e.printStackTrace();
            }

            String langs[] = new String[2];
            langs[0] = "kor";
            langs[1] = "eng";
            datapath = getActivity().getFilesDir().toString() + "/tesseract/";
            for (int i = 0; i < langs.length; i++)
                checkFile(new File(datapath + "tessdata/"), langs[i]);

            mTess = new TessBaseAPI();
            mTess.init(datapath, "eng+kor");
            mTess.setVariable(TessBaseAPI.VAR_CHAR_BLACKLIST, "!@#$%^&*()_=-[]}{;:'\"\\|~`,./<>?");
            processImage();
        }
    }

    Bitmap rotateBitmap(Bitmap bmp) {
        int width = bmp.getWidth();
        int height = bmp.getHeight();

        Matrix matrix = new Matrix();
        matrix.postRotate(90F);
        Bitmap resizedBitmap = Bitmap.createBitmap(bmp, 0, 0, width, height, matrix, true);
        bmp.recycle();

        return resizedBitmap;
    }

    void checkFile(File dir, String lang) {
        //directory does not exist, but we can successfully create it
        if (!dir.exists() && dir.mkdirs()) {
            copyFiles(lang);
        }
        //The directory exists, but there is no data file in it
        if (dir.exists()) {
            String datafilepath = datapath + "/tessdata/" + lang + ".traineddata";
            File datafile = new File(datafilepath);
            if (!datafile.exists()) {
                copyFiles(lang);
            }
        }
    }

    private void copyFiles(String lang) {
        try {
            String filepath = datapath + "/tessdata/" + lang + ".traineddata";
            AssetManager assetManager = getActivity().getAssets();
            InputStream instream = assetManager.open("tessdata/" + lang + ".traineddata");
            OutputStream outstream = new FileOutputStream(filepath);
            byte[] buffer = new byte[1024];
            int read;
            while ((read = instream.read(buffer)) != -1) {
                outstream.write(buffer, 0, read);
            }
            outstream.flush();
            outstream.close();
            instream.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    void processImage() {
        mTess.setImage(image);
        String test = mTess.getUTF8Text();
        Log.e("Result: ", test);
        mTess.end();
        new Thread() {
            @Override
            public void run() {
                super.run();
                try {
                    Call<List<GetBadIngredientModel>> call = ForRestSingleton.getInstance().ingredientPerCosmetic("룩 앳 마이 아이즈", "2");
                    final List<GetBadIngredientModel> result = call.execute().body();
                    getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Intent intent = new Intent(getActivity(), CosmeticInformationActivity.class);
                            intent.putExtra("cname", "룩 앳 마이 아이즈");
                            intent.putExtra("company", "에뛰드하우스");
                            intent.putExtra("check", 0);
                            intent.putExtra("image", R.drawable.look_at_my_eyes);
                            intent.putExtra("ingredient", result.toString());
                            Log.e("asdasd", result.toString());
                            getActivity().startActivity(intent);
                        }
                    });

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }.start();
    }


    public ArrayList<OneImgOneStringCardView> addData(ArrayList<OneImgOneStringCardView> arrayList) {
        arrayList.add(new OneImgOneStringCardView(R.drawable.innisfree_logo, "이니스프리"));
        arrayList.add(new OneImgOneStringCardView(R.drawable.ysl, "입생로랑"));
        arrayList.add(new OneImgOneStringCardView(R.drawable.chanel, "샤넬"));
        arrayList.add(new OneImgOneStringCardView(R.drawable.mac, "맥"));
        return arrayList;
    }

    public void search(String findText, List<String> list, ArrayList<String> arrayList, searchAdapter searchAdapter){
        list.clear();

        if(findText.length() == 0){
            list.addAll(arrayList);
        }
        else{
            for(int i = 0; i<arrayList.size(); i++){
                if(arrayList.get(i).toLowerCase().contains(findText)){
                    list.add(arrayList.get(i));
                }
            }
        }
        searchAdapter.notifyDataSetChanged();
    }
    void settingList(List<String> list){
        list.add("UV 프로텍터 스탠바이유 선스크린");
        list.add("NEW 이데베논 선블럭 SPF50+ PA++++");
        list.add("유비데아 XL 멜트-인 틴티드 크림 SPF50+ PA++++");
        list.add("썬 파우더 로션 SPF 25/PA +++");
        list.add("2mg 선더마 B SPF35 PA++");
        list.add("메디 UV 울트라 선 SPF50+ PA+++");
        list.add("엔조이 마일드 썬 에센스 SPF50+ PA+++");
        list.add("스마트솔루션365 실키 선블록 SPF50 PA+++");
        list.add("바비 브라운");
        list.add("스파클 아이섀도우");
        list.add("컬러 필터 섀도우 팔레트");
        list.add("럭스 아이섀도우");
        list.add("아이섀도");
        list.add("별총총 펄 파우더");
        list.add("매트 앤 메탈 아이섀도우 팔레트");
        list.add("모노큐브 아이섀도우(쉬머)");
        list.add("모노큐브 아이섀도우(쉬머)");
        list.add("모노큐브 아이섀도우(쉬머)");
        list.add("모노큐브 아이섀도우(쉬머)");list.add("모노큐브 아이섀도우(쉬머)");
        list.add("모노큐브 아이섀도우(쉬머)");
        list.add("모노큐브 아이섀도우(쉬머)");


    }

}

//만든 이유
//EditText 커스텀하여 뒤로가기 버튼을 눌렀을 때 editText의 focus제거 해준다.
class ExEditText extends android.support.v7.widget.AppCompatEditText {

    public ExEditText(Context context) {
        super(context);
    }

    public ExEditText(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    @Override
    public boolean onKeyPreIme(int keyCode, KeyEvent event) {
        if (event.getAction() == KeyEvent.ACTION_DOWN) {
            if (keyCode == KeyEvent.KEYCODE_BACK) {
                this.clearFocus();
                return true;
            }
        }
        return super.onKeyPreIme(keyCode, event);
    }
}
