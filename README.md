# EcoBudget 🌿

Dépôt de base pour le projet du cours de développement mobile avancé.

## Migration Kotlin Multiplatform (KMP)

La logique métier a été déplacée de `app` vers un module partagé `shared`, compilé pour Android et iOS (`iosX64`, `iosArm64`, `iosSimulatorArm64`). Le module `app` garde uniquement l'interface Jetpack Compose et dépend de `:shared`.

### Organisation du module `shared`

| Dossier | Contenu |
| --- | --- |
| `commonMain/kotlin/com/example/model` | `Category`, `Transaction`, `YearMonth` |
| `commonMain/kotlin/com/example/data/repository` | `TransactionRepository`, `FakeTransactionRepository` |
| `commonMain/kotlin/com/example/viewmodel` | `EcoBudgetViewModel`, `EcoBudgetUiState` |
| `commonMain/kotlin/com/example/utils` | Déclarations `expect` : `generateUUID()`, `getCurrentTimeMillis()` |
| `androidMain/`, `iosMain/` | Implémentations `actual` (`java.util.UUID` / `NSUUID`, `System` / `NSDate`) |
| `commonMain/composeResources` | Chaînes de l'interface (Compose Multiplatform Resources) |
| `commonTest/` | Tests communs (`EcoBudgetTest`) |

### Changements principaux

- `YearMonth` utilise `kotlinx-datetime` au lieu de `java.util.Calendar`, qui n'existe pas en `commonMain`.
- Le ViewModel utilise `org.jetbrains.androidx.lifecycle:lifecycle-viewmodel`, la version multiplateforme.
- Les chaînes passent de `R.string` à `Res.string` (package `com.example.shared.resources`). Seul `app_name` reste dans `app/src/main/res`, car le manifest Android en a besoin.
- Les libellés des catégories viennent de `getCategoryLabel()` dans `app`, puisque `Category` ne peut plus référencer de ressource Android.

### Compiler et tester

```bash
./gradlew assembleDebug                          # APK Android
./gradlew :shared:compileCommonMainKotlinMetadata # code commun
./gradlew :shared:testDebugUnitTest              # tests communs (JVM Android)
./gradlew :app:testDebugUnitTest                 # tests Robolectric de l'app
```

Le framework iOS (`shared.framework`) ne peut être compilé que sur macOS.

Les tests Robolectric tournent en mode SQLite et graphique `LEGACY` (voir `app/build.gradle.kts`). Le runtime natif de Robolectric ne se charge pas quand le chemin du projet contient un espace.
