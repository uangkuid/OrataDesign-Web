package com.oratakashi.design.docs.di

import com.oratakashi.design.docs.data.remote.service.MavenApiService
import com.oratakashi.design.docs.data.remote.service.MavenApiServiceImpl
import com.oratakashi.design.docs.data.repository.MavenRepositoryImpl
import com.oratakashi.design.docs.domain.repository.MavenRepository
import com.oratakashi.design.docs.domain.usecase.GetMavenMetadataUseCase
import com.oratakashi.design.docs.ui.maven.MavenViewModel
import io.ktor.client.HttpClient
import io.ktor.client.engine.cio.CIO
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.serialization.kotlinx.xml.xml
import nl.adaptivity.xmlutil.XmlDeclMode
import nl.adaptivity.xmlutil.serialization.XML
import org.koin.core.module.Module
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

object AppModule {
    val repositoryModule = module {
        single<MavenRepository> { MavenRepositoryImpl(get()) }
    }

    val useCaseModule = module {
        factory { GetMavenMetadataUseCase(get()) }
    }

    val viewModelModule = module {
        viewModel { MavenViewModel(get()) }
    }

    val apiModule = module {
        single<MavenApiService> { MavenApiServiceImpl(get()) }
    }

    val networkModule = module {
        single {
            HttpClient(CIO) {
                install(ContentNegotiation) {
                    xml(
                        format = XML {
                            // Configuration untuk XML parser
                            autoPolymorphic = false
                            indentString = "  "
                            xmlDeclMode = XmlDeclMode.Auto
                        }
                    )
                }
            }
        }
    }

    fun provideModule(): Array<Module> {
        return arrayOf(
            networkModule,
            apiModule,
            repositoryModule,
            useCaseModule,
            viewModelModule
        )
    }
}